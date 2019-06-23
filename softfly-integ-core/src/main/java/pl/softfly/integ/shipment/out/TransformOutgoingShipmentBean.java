package pl.softfly.integ.shipment.out;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.transformation.DocumentTransformation;
import pl.softfly.integ.doc.transformation.DocumentTransformationBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;


/**
 * {@inheritDoc}
 *
 * @author Grzegorz Ziemski
 */
public class TransformOutgoingShipmentBean extends OutgoingShipmentBean {

  private static final Logger LOGGER =
      Logger.getLogger(TransformOutgoingShipmentBean.class.getName());

  protected DocumentTransformation documentTransformation = new DocumentTransformationBean();

  /**
   * 1.Call {@link OutgoingShipmentBean#determine(DocumentHeader, Participant)}<br>
   * If not empty, use case end.<br>
   * 2. Find current bodies.<br>
   * 3. Find endpoints where<br>
   * endpoint.documentFormat.documentBusinessType = documentHeader.documentBusinessType<br>
   * endpoint.participant = participant<br>
   * 3.1. {@link DocumentTransformation#isSupported} Check if it is possible to transform into the
   * supported document format by {@link Endpoint}.<br>
   * 4. Create the {@link ShipmentOutgoing}.<br>
   *
   * @return {@link ShipmentOutgoing} with the ascending cost.
   */
  @Override
  protected Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader,
      Participant participant) {
    // 1
    Collection<ShipmentOutgoing> shipmentsC = super.determine(documentHeader, participant);
    if (CollectionUtils.isNotEmpty(shipmentsC)) {
      return shipmentsC;
    }
    // 2
    List<DocumentBody> bodies = getDocumentHeaderRepository().findCurrent(documentHeader);
    // 3
    List<Endpoint> endpoints =
        getEndpointRepository().findBy(participant, documentHeader.getDocumentBusinessType());
    TreeSet<ShipmentOutgoing> shipments = new TreeSet<>(new CostAscOutgoingShipmentComparator());
    for (Endpoint endpoint : endpoints) {
      for (DocumentBody body : bodies) {
        final DocumentFormat sourceDocumentFormat = body.getDocumentFormat();
        final DocumentFormat targetDocumentFormat = endpoint.getDocumentFormat();

        ShipmentOutgoing shipment = new ShipmentOutgoing();
        shipment.setEndpoint(endpoint);
        shipment.setDocumentBody(body);
        shipment.setStatus(ShipmentOutgoingStatus.NONE);

        // Transformation is not needed. Skipped because it has been checked by {@link
        // OutgoingShipmentBean}
        boolean added = !sourceDocumentFormat.equals(targetDocumentFormat);
        // 3.1.
        boolean supported =
            getDocumentTransformation().isSupported(sourceDocumentFormat, targetDocumentFormat);
        if (added && supported && !documentHeader.getShipments().contains(shipment)) {
          // 4. With transform
          shipment.setCost(100);// TODO implement the cost system
          shipment.setStatus(ShipmentOutgoingStatus.AWAITING_SEND);
          shipments.add(shipment);
        }
      }
    }

    return convertToList(shipments.first());
  }

  public DocumentTransformation getDocumentTransformation() {
    return documentTransformation;
  }

  public void setDocumentTransformation(DocumentTransformation documentTransformation) {
    this.documentTransformation = documentTransformation;
  }

}
