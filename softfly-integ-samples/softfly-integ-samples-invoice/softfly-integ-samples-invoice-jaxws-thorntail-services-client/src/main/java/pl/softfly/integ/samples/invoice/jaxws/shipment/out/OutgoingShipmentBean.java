package pl.softfly.integ.samples.invoice.jaxws.shipment.out;

import java.util.Collection;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

public class OutgoingShipmentBean implements pl.softfly.integ.shipment.out.OutgoingShipment {

  private OutgoingShipment transformOutgoingShipment = new OutgoingShipment();

  private TransformOutgoingShipmentService transformOutgoingShipmentService =
      transformOutgoingShipment.getTransformOutgoingShipmentServicePort();

  @Override
  public Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader) {
    return transformOutgoingShipmentService.determine(documentHeader);
  }
}
