package pl.softfly.oipf.document.validation.business.ws;

import pl.softfly.oipf.document.validation.business.DocumentBusinessValidationBean;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class DocumentBusinessValidationWS extends DocumentBusinessValidationBean {

    @WebMethod
    public List<?> valid(DocumentBody documentBody) {
        return super.valid(documentBody);
    }

}
