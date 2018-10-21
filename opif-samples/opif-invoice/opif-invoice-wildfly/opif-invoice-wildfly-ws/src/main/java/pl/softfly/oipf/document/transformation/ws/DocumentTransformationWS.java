package pl.softfly.oipf.document.transformation.ws;

import pl.softfly.oipf.document.transformation.DocumentTransformationBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class DocumentTransformationWS extends DocumentTransformationBean {

    @WebMethod
    public DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat) {
        return super.transform(sourceDocumentBody, targetDocumentFormat);
    }

}
