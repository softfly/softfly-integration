package pl.softfly.integ.shipment.out;

import java.util.List;
import java.util.TreeSet;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.transformation.DocumentTransformation;
import pl.softfly.integ.doc.transformation.DocumentTransformationBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;


public class TransformOutgoingShipmentBean extends OutgoingShipmentBean {

  protected DocumentTransformation documentTransformation = new DocumentTransformationBean();

  /**
   * <ul>
   * <li>1.Call {@link OutgoingShipmentBean#determine(DocumentHeader, Participant)}<br>
   * If not empty, use case end.<br>
   * <li>2. Find current bodies.<br>
   * <li>3. Find endpoints where<br>
   * endpoint.documentFormat.documentBusinessType = documentHeader.documentBusinessType<br>
   * endpoint.participant = participant<br>
   * <li>3.1. {@link DocumentTransformation#isSupported} Check if it is possible to transform into the
   * supported document format by {@link Endpoint}.<br>
   * <li>4. Create the {@link ShipmentOutgoing}.<br>
   *
   * @return {@link ShipmentOutgoing} with the ascending cost.
   * @throws CloneNotSupportedException
   */
  @Override
  protected ShipmentOutgoing determine(DocumentHeader documentHeader, Participant participant) {
    // 1
    ShipmentOutgoing shipmentP = super.determine(documentHeader, participant);
    if (shipmentP != null) {
      return shipmentP;
    }
    // 2
    List<DocumentBody> bodies = getDocumentHeaderRepository().findCurrent(documentHeader);
    // 3
    int nextShipmentId = getDocumentHeaderRepository().findNextShipmentId(documentHeader);
    List<Endpoint> endpoints =
        getEndpointRepository().findBy(participant, documentHeader.getDocumentBusinessType());
    TreeSet<ShipmentOutgoing> shipmentsOut = new TreeSet<>(new CostAscOutgoingShipmentComparator());
    for (Endpoint endpoint : endpoints) {
      for (DocumentBody body : bodies) {
        final DocumentFormat sourceDocumentFormat = body.getDocumentFormat();
        final DocumentFormat targetDocumentFormat = endpoint.getDocumentFormat();

        ShipmentOutgoing shipmentOut = new ShipmentOutgoing();
        shipmentOut.setId(nextShipmentId);
        shipmentOut.setEndpoint(endpoint);
        shipmentOut.setDocumentBody(getDocumentMapper().cloneShortDocumentBody(body));

        // Transformation is not needed. Skipped because it has been checked by {@link
        // OutgoingShipmentBean}
        boolean added = !sourceDocumentFormat.equals(targetDocumentFormat);
        // 3.1.
        boolean supported =
            getDocumentTransformation().isSupported(sourceDocumentFormat, targetDocumentFormat);
        if (added && supported && !documentHeader.getShipments().contains(shipmentsOut)) {
          // 4. With transform
          shipmentOut.setCost(100);// TODO implement the cost system
          shipmentOut.setStatus(ShipmentOutgoingStatus.AWAITING_SEND);
          shipmentsOut.add(shipmentOut);
        }
      }
    }

    return shipmentsOut.first();
  }

  public DocumentTransformation getDocumentTransformation() {
    return documentTransformation;
  }

  public void setDocumentTransformation(DocumentTransformation documentTransformation) {
    this.documentTransformation = documentTransformation;
  }

}
