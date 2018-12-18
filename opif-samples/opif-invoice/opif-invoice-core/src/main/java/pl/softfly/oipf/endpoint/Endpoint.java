package pl.softfly.oipf.endpoint;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.Participant;

public interface Endpoint {

	boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint);
	
}
