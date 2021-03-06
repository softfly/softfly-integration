package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.10 Generated source version: 2.1
 */
@WebService(name = "DocumentTransformationService", targetNamespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/")
@XmlSeeAlso({
    pl.softfly.integ.doc.entity.ObjectFactory.class,
    pl.softfly.integ.samples.invoice.jaxws.doc.transformation.ObjectFactory.class
})
public interface DocumentTransformationService {


  /**
   * @param arg1
   * @param arg0
   * @return returns pl.softfly.integ.doc.entity.DocumentBody
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "transform", targetNamespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.transformation.Transform")
  @ResponseWrapper(localName = "transformResponse", targetNamespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.transformation.TransformResponse")
  public DocumentBody transform(
      @WebParam(name = "arg0", targetNamespace = "")
          DocumentBody arg0,
      @WebParam(name = "arg1", targetNamespace = "")
          DocumentFormat arg1);

  /**
   * @param arg1
   * @param arg0
   * @return returns boolean
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "isSupported", targetNamespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.transformation.IsSupported")
  @ResponseWrapper(localName = "isSupportedResponse", targetNamespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.doc.transformation.IsSupportedResponse")
  public boolean isSupported(
      @WebParam(name = "arg0", targetNamespace = "")
          DocumentFormat arg0,
      @WebParam(name = "arg1", targetNamespace = "")
          DocumentFormat arg1);

}
