package pl.softfly.oipf.endpoint;

import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Participant;

public interface Endpoint {

    DocumentShipment prepareShipment(DocumentHeader documentHeader, Participant recipient);

    DocumentShipment send(DocumentHeader documentHeader, Participant recipient);

    DocumentShipment send(DocumentShipment shipment);

    boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint);
}
