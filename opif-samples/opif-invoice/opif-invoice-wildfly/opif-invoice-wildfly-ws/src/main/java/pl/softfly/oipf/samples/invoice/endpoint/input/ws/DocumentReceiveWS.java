package pl.softfly.oipf.samples.invoice.endpoint.input.ws;

import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.samples.invoice.processes.ejb.MainFlowEJB;
import pl.softfly.oipf.utils.LoggerUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.logging.Logger;

@WebService
public class DocumentReceiveWS extends MainFlowEJB {

    private final static Logger LOGGER = Logger.getLogger(DocumentReceiveWS.class.getName());

    @WebMethod
    public DocumentHeader send(String inputDocument) {
        LoggerUtil.start(LOGGER);
        LOGGER.info(inputDocument);
        DocumentHeader documentHeader = start(inputDocument);
        LoggerUtil.end(LOGGER);
        return documentHeader;
    }
}
