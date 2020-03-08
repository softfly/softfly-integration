package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.transformation.DocumentTransformationBean;


/**
 * http://192.168.28.1:8080/DocumentTransformation
 */
@WebService(serviceName = "DocumentTransformation")
public class DocumentTransformationService extends DocumentTransformationBean {

  @Override
  @WebMethod
  public boolean isSupported(DocumentFormat sourceDocumentFormat,
      DocumentFormat targetDocumentFormat) {
    return super.isSupported(sourceDocumentFormat, targetDocumentFormat);
  }

  @Override
  @WebMethod
  public DocumentBody transform(DocumentBody sourceDocumentBody,
      DocumentFormat targetDocumentFormat) {
    return super.transform(sourceDocumentBody, targetDocumentFormat);
  }

}
