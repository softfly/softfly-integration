package pl.softfly.integ.endpoint;

import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

/**
 * Send the document via endpoint.
 */
public interface SenderEndpoint {

  ShipmentOutgoing send(ShipmentOutgoing shipment);
}
