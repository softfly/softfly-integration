package pl.softfly.oipf.endpoint.ws;

import pl.softfly.oipf.endpoint.EndpointBean;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class EndpointWS extends EndpointBean {

    @WebMethod
    public boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint) {
        return super.send(documentBody, endpoint);
    }
}
