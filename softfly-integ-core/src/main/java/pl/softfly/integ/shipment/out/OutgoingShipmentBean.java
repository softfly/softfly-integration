package pl.softfly.integ.shipment.out;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.repository.DocumentHeaderRepositoryBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.endpoint.repository.EndpointRepositoryBean;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.Shipment;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;


/**
 * Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.
 *
 * <ol>
 * <li>Call {@link #isRequired}.
 * <li>Call {@link #determine}.
 * <li>Send shipments by {@link pl.softfly.integ.endpoint.SenderEndpoint}.
 * <li>Call again "isRequired" to check if shipments have been sent correctly.<br>
 * If you correctly end the use case,<br>
 * otherwise try to determine the next shipments using other endpoints, try again after the timeout
 * in step 2.
 * </ol>
 *
 * @author Grzegorz Ziemski
 */
public class OutgoingShipmentBean implements OutgoingShipment {

  private static final Logger LOGGER = Logger.getLogger(OutgoingShipmentBean.class.getName());

  protected EndpointRepositoryBean endpointRepository = new EndpointRepositoryBean();

  protected DocumentHeaderRepositoryBean documentHeaderRepository =
      new DocumentHeaderRepositoryBean();

  /**
   * <ol>
   * <li>Check if the shipment has been determined.<br>
   * If not, the shipment require to be determined (TRUE).
   * <li>Check if the shipment has been sent to every recipient.<br>
   * If not, the shipment require to be determined (TRUE).<br>
   * <li>Otherwise the shipment does not require to be determined (FALSE).
   * </ol>
   */
  @Override
  public boolean isRequired(DocumentHeader documentHeader) {
    // 1
    if (!existShipmentOutgoing(documentHeader)) {
      LOGGER.info("The shipment outgoing require to be determined.");
      return true;
    }

    // 2
    Map<Participant, Boolean> check = checkParticapantSend(documentHeader);
    if (checkEveryParticapantSend(check)) {
      LOGGER.info("The shipment outgoing does not require to be determined.");
      return false;
    } else {
      LOGGER.info("The shipment outgoing require to be determined.");
      return true;
    }
  }

  /**
   * 2. Check if the shipment has been determined.
   */
  protected boolean existShipmentOutgoing(DocumentHeader documentHeader) {
    for (Shipment shipment : documentHeader.getShipments()) {
      if (shipment instanceof ShipmentOutgoing) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if the shipment has been sent to recipients.
   */
  protected Map<Participant, Boolean> checkParticapantSend(DocumentHeader documentHeader) {
    Map<Participant, Boolean> check = new LinkedHashMap<>();
    for (Shipment shipment : documentHeader.getShipments()) {
      if (shipment instanceof ShipmentOutgoing) {
        ShipmentOutgoing shipmentOut = (ShipmentOutgoing) shipment;
        Participant p = shipmentOut.getEndpoint().getParticipant();
        setParticipantSend(check, p,
            shipmentOut.getStatus().equals(ShipmentOutgoingStatus.COMITTED));
      }
    }
    return check.isEmpty() ? null : check;
  }

  protected void setParticipantSend(Map<Participant, Boolean> checked, Participant key,
      boolean value) {
    if (value) {
      checked.put(key, true);
    } else {
      Boolean oldValue = checked.get(key);
      if (Objects.isNull(oldValue)) {
        checked.put(key, false);
      }
    }
  }

  /**
   * 2. Check if the shipment has been sent to every recipient.
   */
  protected boolean checkEveryParticapantSend(Map<Participant, Boolean> check) {
    for (Map.Entry<Participant, Boolean> entry : check.entrySet()) {
      if (!entry.getValue()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Create one ShipmentOutgoing for each recipient.
   */
  @Override
  public Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader) {
    LOGGER.info(
        "Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.");
    List<ShipmentOutgoing> shipments = new LinkedList<>();

    for (Participant participant : documentHeader.getRecipients()) {
      shipments.addAll(determine(documentHeader, participant));
    }

    return shipments;
  }

  /**
   * 1. Find current bodies.<br>
   * 2. Find endpoints where<br>
   * endpoint.documentFormat = body.documentFormat<br>
   * endpoint.participant = participant<br>
   * 3. Create the {@link ShipmentOutgoing}.
   */
  protected Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader,
      Participant participant) {
    for (DocumentBody body : getDocumentHeaderRepository().findCurrent(documentHeader)) {
      List<Endpoint> endpoints =
          getEndpointRepository().findBy(participant, body.getDocumentFormat());
      for (Endpoint endpoint : endpoints) {
        ShipmentOutgoing shipment = new ShipmentOutgoing();
        shipment.setEndpoint(endpoint);
        shipment.setDocumentBody(body);
        shipment.setCost(0);

        if (!documentHeader.getShipments().contains(shipment)) {
          shipment.setStatus(ShipmentOutgoingStatus.AWAITING_SEND);
          return convertToList(shipment);
        }
      }
    }
    return null;
  }

  protected List<ShipmentOutgoing> convertToList(ShipmentOutgoing shipment) {
    List<ShipmentOutgoing> shipments = new LinkedList<>();
    shipments.add(shipment);
    return shipments;
  }

  public EndpointRepositoryBean getEndpointRepository() {
    return endpointRepository;
  }

  public void setEndpointRepository(EndpointRepositoryBean endpointRepository) {
    this.endpointRepository = endpointRepository;
  }

  public DocumentHeaderRepositoryBean getDocumentHeaderRepository() {
    return documentHeaderRepository;
  }

  public void setDocumentHeaderRepository(DocumentHeaderRepositoryBean documentHeaderRepository) {
    this.documentHeaderRepository = documentHeaderRepository;
  }

}
