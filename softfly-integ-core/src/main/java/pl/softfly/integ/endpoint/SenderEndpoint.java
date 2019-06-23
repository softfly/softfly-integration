package pl.softfly.integ.endpoint;

import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

/**
 * Send the document via endpoint.
 *
 * @author Grzegorz Ziemski
 */
public interface SenderEndpoint {

  boolean send(DocumentBody documentBody, Endpoint endpoint);

  boolean send(ShipmentOutgoing shipment);
}
