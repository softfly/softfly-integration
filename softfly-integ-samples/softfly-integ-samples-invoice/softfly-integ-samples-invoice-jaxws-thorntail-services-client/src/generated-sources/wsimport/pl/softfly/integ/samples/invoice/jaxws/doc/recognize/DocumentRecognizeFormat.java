package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.10 Generated source version: 2.1
 */
@WebServiceClient(name = "DocumentRecognizeFormat", targetNamespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", wsdlLocation = "http://localhost:8080/DocumentRecognizeFormat?wsdl")
public class DocumentRecognizeFormat
    extends Service {

  private final static URL DOCUMENTRECOGNIZEFORMAT_WSDL_LOCATION;
  private final static WebServiceException DOCUMENTRECOGNIZEFORMAT_EXCEPTION;
  private final static QName DOCUMENTRECOGNIZEFORMAT_QNAME = new QName(
      "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", "DocumentRecognizeFormat");

  static {
    URL url = null;
    WebServiceException e = null;
    try {
      url = new URL("http://localhost:8080/DocumentRecognizeFormat?wsdl");
    } catch (MalformedURLException ex) {
      e = new WebServiceException(ex);
    }
    DOCUMENTRECOGNIZEFORMAT_WSDL_LOCATION = url;
    DOCUMENTRECOGNIZEFORMAT_EXCEPTION = e;
  }

  public DocumentRecognizeFormat() {
    super(__getWsdlLocation(), DOCUMENTRECOGNIZEFORMAT_QNAME);
  }

  public DocumentRecognizeFormat(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  private static URL __getWsdlLocation() {
    if (DOCUMENTRECOGNIZEFORMAT_EXCEPTION != null) {
      throw DOCUMENTRECOGNIZEFORMAT_EXCEPTION;
    }
    return DOCUMENTRECOGNIZEFORMAT_WSDL_LOCATION;
  }

  /**
   * @return returns DocumentRecognizeFormatService
   */
  @WebEndpoint(name = "DocumentRecognizeFormatServicePort")
  public DocumentRecognizeFormatService getDocumentRecognizeFormatServicePort() {
    return super.getPort(new QName("http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/",
        "DocumentRecognizeFormatServicePort"), DocumentRecognizeFormatService.class);
  }

  /**
   * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
   *                 Supported features not in the <code>features</code> parameter will have their
   *                 default values.
   * @return returns DocumentRecognizeFormatService
   */
  @WebEndpoint(name = "DocumentRecognizeFormatServicePort")
  public DocumentRecognizeFormatService getDocumentRecognizeFormatServicePort(
      WebServiceFeature... features) {
    return super.getPort(new QName("http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/",
        "DocumentRecognizeFormatServicePort"), DocumentRecognizeFormatService.class, features);
  }

}