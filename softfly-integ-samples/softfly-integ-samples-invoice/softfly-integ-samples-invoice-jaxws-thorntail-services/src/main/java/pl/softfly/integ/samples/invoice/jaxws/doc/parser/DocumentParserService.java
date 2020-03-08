package pl.softfly.integ.samples.invoice.jaxws.doc.parser;

import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.parser.DocumentParserBean;

/**
 * http://localhost:8080/DocumentParser?wsdl
 */
@WebService(serviceName = "DocumentParser")
public class DocumentParserService extends DocumentParserBean {

  @Override
  @WebMethod
  public List<DocumentFormat> getSupported(Set<DocumentBusinessType> documentBusinessType) {
    return super.getSupported(documentBusinessType);
  }

  @Override
  @WebMethod
  public DocumentHeader parse(DocumentHeader documentHeader) {
    return super.parse(documentHeader);
  }

}
