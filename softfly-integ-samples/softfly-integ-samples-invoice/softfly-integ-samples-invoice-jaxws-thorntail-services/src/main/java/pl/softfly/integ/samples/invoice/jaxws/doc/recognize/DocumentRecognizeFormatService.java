package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormatBean;


/**
 * http://localhost:8080/DocumentRecognizeFormat?wsdl
 */
@WebService(serviceName = "DocumentRecognizeFormat")
public class DocumentRecognizeFormatService extends DocumentRecognizeFormatBean {

  @Override
  @WebMethod
  public DocumentHeader recognize(DocumentHeader documentHeader) {
    return super.recognize(documentHeader);
  }

}
