package pl.softfly.oipf.endpoint.ws;

import pl.softfly.oipf.endpoint.EndpointBean;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.Participant;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class EndpointWS extends EndpointBean {

    @WebMethod
    public boolean send(DocumentBody documentBody, Participant participant) {
        return super.send(documentBody, participant);
    }
}
