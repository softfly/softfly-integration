package pl.softfly.oipf.document.recognize.ws;

import pl.softfly.oipf.document.recognize.DocumentRecognizeBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class DocumentRecognizeWS extends DocumentRecognizeBean {

    @WebMethod
    public DictDocumentFormat recognize(DocumentBody documentBody) {
        return super.recognize(documentBody);
    }

}
