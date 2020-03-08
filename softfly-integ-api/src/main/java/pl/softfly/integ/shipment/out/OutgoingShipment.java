package pl.softfly.integ.shipment.out;

import java.util.Collection;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.
 */
public interface OutgoingShipment {

  /**
   * <p>
   * Tries to create one {@link ShipmentOutgoing} for each recipient.
   * <p>
   * The method can be called multiple times. Each next call determine next {@link ShipmentOutgoing}
   * based on the state of previous {@link ShipmentOutgoing}.
   */
  Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader);

}
