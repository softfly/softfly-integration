package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.10 Generated source version: 2.1
 */
@WebService(name = "DocumentRecognizeFormatService", targetNamespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/")
@XmlSeeAlso({
    pl.softfly.integ.doc.entity.ObjectFactory.class,
    pl.softfly.integ.endpoint.entity.ObjectFactory.class,
    pl.softfly.integ.entity.ObjectFactory.class,
    pl.softfly.integ.samples.invoice.jaxws.doc.recognize.ObjectFactory.class,
    pl.softfly.integ.shipment.entity.ObjectFactory.class
})
public interface DocumentRecognizeFormatService {


  /**
   * @param arg0
   * @return returns pl.softfly.integ.doc.entity.DocumentHeader
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "recognize", targetNamespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.recognize.Recognize")
  @ResponseWrapper(localName = "recognizeResponse", targetNamespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.recognize.RecognizeResponse")
  public DocumentHeader recognize(
      @WebParam(name = "arg0", targetNamespace = "")
          DocumentHeader arg0);

}
