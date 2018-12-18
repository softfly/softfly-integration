package pl.softfly.oipf.endpoint;

import pl.softfly.oipf.entity.DocumentBody;

public interface Endpoint {

    boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint);

}
