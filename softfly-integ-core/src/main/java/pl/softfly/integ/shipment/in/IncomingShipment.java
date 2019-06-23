package pl.softfly.integ.shipment.in;

import pl.softfly.integ.shipment.entity.ShipmentIncoming;

/**
 * Create {@link ShipmentIncoming} with details from where it came from.
 */
public interface IncomingShipment {

  ShipmentIncoming create(String inputText);

}
