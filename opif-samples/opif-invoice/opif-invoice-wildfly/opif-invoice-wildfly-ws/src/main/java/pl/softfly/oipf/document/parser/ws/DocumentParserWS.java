package pl.softfly.oipf.document.parser.ws;

import pl.softfly.oipf.document.parser.DocumentParserBean;
import pl.softfly.oipf.entity.DocumentBody;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class DocumentParserWS extends DocumentParserBean {

    @WebMethod
    public DocumentBody parse(DocumentBody documentBody) {
        return super.parse(documentBody);
    }

}
