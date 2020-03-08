package pl.softfly.integ.samples.invoice.jaxws.doc.validation.business;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.validation.business.DocumentValidationBusinessBean;

/**
 * http://localhost:8080/DocumentValidationBusiness?wsdl
 */
@WebService(serviceName = "DocumentValidationBusiness")
public class DocumentValidationBusinessService extends DocumentValidationBusinessBean {

  @WebMethod
  @Override
  public List<DocumentFormat> getSupported(List<DocumentBusinessType> documentBusinessType) {
    return super.getSupported(documentBusinessType);

  }

  @WebMethod
  @Override
  public DocumentHeader validate(DocumentHeader documentHeader) {
    return super.validate(documentHeader);
  }
}
