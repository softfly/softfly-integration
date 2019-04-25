package pl.softfly.oipf.document.validation.scheme.ws;

import pl.softfly.oipf.document.validation.schema.DocumentValidationSchemeBean;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class DocumentValidationSchemeWS extends DocumentValidationSchemeBean {

    @WebMethod
    public List<?> valid(DocumentBody documentBody) {
        return super.valid(documentBody);
    }

}
