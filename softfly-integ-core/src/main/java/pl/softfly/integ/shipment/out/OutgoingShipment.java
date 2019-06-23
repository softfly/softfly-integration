package pl.softfly.integ.shipment.out;

import java.util.Collection;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.
 *
 * <ol>
 * <li>Call {@link #isRequired}.
 * <li>Call {@link #determine}.
 * <li>Send shipments by {@link pl.softfly.integ.endpoint.SenderEndpoint}.
 * <li>Call again "isRequired" to check if shipments have been sent correctly. If you correctly end
 * the use case, otherwise try to determine the next shipments using other endpoints, try again
 * after the timeout in step 2.
 * </ol>
 *
 * @author Grzegorz Ziemski
 */
public interface OutgoingShipment {

  boolean isRequired(DocumentHeader documentHeader);

  Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader);

}
