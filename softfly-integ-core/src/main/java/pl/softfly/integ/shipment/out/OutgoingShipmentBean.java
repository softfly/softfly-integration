package pl.softfly.integ.shipment.out;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.EqualsBuilder;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.repository.DocumentHeaderRepositoryBean;
import pl.softfly.integ.doc.utils.DocumentMapperBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.endpoint.repository.EndpointRepositoryBean;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.Shipment;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;


public class OutgoingShipmentBean implements OutgoingShipment {

  private static final Logger LOGGER = Logger.getLogger(OutgoingShipmentBean.class.getName());

  protected EndpointRepositoryBean endpointRepository = new EndpointRepositoryBean();

  protected DocumentMapperBean documentMapper = new DocumentMapperBean();

  protected DocumentHeaderRepositoryBean documentHeaderRepository =
      new DocumentHeaderRepositoryBean();

  protected IsOutgoingShipmentRequiredBean isOutgoingShipmentRequired =
      new IsOutgoingShipmentRequiredBean();

  protected boolean isRequired(DocumentHeader documentHeaderIn) {
    return isOutgoingShipmentRequired.isRequired(documentHeaderIn);
  }

  @Override
  public Collection<ShipmentOutgoing> determine(DocumentHeader documentHeaderIn) {
    if (isRequired(documentHeaderIn)) {
      LOGGER.info(
          "Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.");
      return documentHeaderIn.getRecipients().stream()
          .map(participantIn -> determine(documentHeaderIn, participantIn))
          .collect(Collectors.toList());
    } else {
      return null;
    }
  }

  /**
   * <ul>
   * <li>
   * 1. Find current bodies.
   * <li>
   * 2. Find endpoints where<br/>
   * endpoint.documentFormat = body.documentFormat<br/>
   * AND endpoint.participant = participant
   * <li>
   * 3. Create the {@link ShipmentOutgoing}.
   */
  protected ShipmentOutgoing determine(DocumentHeader documentHeaderIn, Participant participantIn) {
    int nextShipmentId = getDocumentHeaderRepository().findNextShipmentId(documentHeaderIn);
    for (DocumentBody bodyIn : getDocumentHeaderRepository().findCurrent(documentHeaderIn)) {
      List<Endpoint> endpoints =
          getEndpointRepository().findBy(participantIn, bodyIn.getDocumentFormat());
      for (Endpoint endpoint : endpoints) {
        ShipmentOutgoing shipmentOut = new ShipmentOutgoing();
        shipmentOut.setEndpoint(endpoint);
        shipmentOut.setCost(0);
        shipmentOut.setDocumentBody(documentMapper.cloneShortDocumentBody(bodyIn));

        if (!contains(documentHeaderIn.getShipments(), shipmentOut)) {
          shipmentOut.setId(nextShipmentId++);
          shipmentOut.setStatus(ShipmentOutgoingStatus.AWAITING_SEND);
          return shipmentOut;
        }
      }
    }
    return null;
  }

  protected boolean contains(final List<Shipment> shipments, final Shipment s2) {
    if (shipments == null) return false;
    return shipments.stream().filter(s1 -> equals(s1, s2)).findFirst().isPresent();
  }

  protected boolean equals(Shipment s1, Shipment s2) {
    return new EqualsBuilder().append(s1.getDocumentBody().getId(), s2.getDocumentBody().getId())//
        .append(s1.getEndpoint().getId(), s2.getEndpoint().getId()).isEquals();
  }

  public EndpointRepositoryBean getEndpointRepository() {
    return endpointRepository;
  }

  public void setEndpointRepository(EndpointRepositoryBean endpointRepository) {
    this.endpointRepository = endpointRepository;
  }

  public DocumentMapperBean getDocumentMapper() {
    return documentMapper;
  }

  public void setDocumentMapper(DocumentMapperBean documentMapper) {
    this.documentMapper = documentMapper;
  }

  public DocumentHeaderRepositoryBean getDocumentHeaderRepository() {
    return documentHeaderRepository;
  }

  public void setDocumentHeaderRepository(DocumentHeaderRepositoryBean documentHeaderRepository) {
    this.documentHeaderRepository = documentHeaderRepository;
  }

  public IsOutgoingShipmentRequiredBean getIsOutgoingShipmentRequired() {
    return isOutgoingShipmentRequired;
  }

  public void setIsOutgoingShipmentRequired(
      IsOutgoingShipmentRequiredBean isOutgoingShipmentRequired) {
    this.isOutgoingShipmentRequired = isOutgoingShipmentRequired;
  }
}
