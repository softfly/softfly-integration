package pl.softfly.integ.samples.invoice.jaxws.doc.validation.schema;

import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchemaBean;

/**
 * http://localhost:8080/DocumentValidationSchema?wsdl
 */
@WebService(serviceName = "DocumentValidationSchema")
public class DocumentValidationSchemaService extends DocumentValidationSchemaBean {

  @Override
  @WebMethod
  public DocumentHeader validate(DocumentHeader documentHeader) {
    return super.validate(documentHeader);
  }
}
