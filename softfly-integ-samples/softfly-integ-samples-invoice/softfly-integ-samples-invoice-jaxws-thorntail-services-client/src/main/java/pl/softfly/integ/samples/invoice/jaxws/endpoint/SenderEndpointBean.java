package pl.softfly.integ.samples.invoice.jaxws.endpoint;

import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

public class SenderEndpointBean implements pl.softfly.integ.endpoint.SenderEndpoint {

  private SenderEndpoint senderEndpoint = new SenderEndpoint();

  private SenderEndpointService senderEndpointService =
      senderEndpoint.getSenderEndpointServicePort();

  @Override
  public ShipmentOutgoing send(ShipmentOutgoing shipment) {
    return senderEndpointService.send(shipment);
  }


}
