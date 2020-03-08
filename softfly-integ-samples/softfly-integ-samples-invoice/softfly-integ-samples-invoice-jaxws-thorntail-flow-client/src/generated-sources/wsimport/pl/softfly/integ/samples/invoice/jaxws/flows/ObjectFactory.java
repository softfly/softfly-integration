package pl.softfly.integ.samples.invoice.jaxws.flows;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.samples.invoice.jaxws.flows package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _StartResponse_QNAME = new QName(
      "http://flows.jaxws.invoice.samples.integ.softfly.pl/", "startResponse");
  private final static QName _Start_QNAME = new QName(
      "http://flows.jaxws.invoice.samples.integ.softfly.pl/", "start");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.samples.invoice.jaxws.flows
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Start }
   */
  public Start createStart() {
    return new Start();
  }

  /**
   * Create an instance of {@link StartResponse }
   */
  public StartResponse createStartResponse() {
    return new StartResponse();
  }

  /**
   * Create an instance of {@link ForwardDocumentFlowResponse }
   */
  public ForwardDocumentFlowResponse createForwardDocumentFlowResponse() {
    return new ForwardDocumentFlowResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link StartResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://flows.jaxws.invoice.samples.integ.softfly.pl/", name = "startResponse")
  public JAXBElement<StartResponse> createStartResponse(StartResponse value) {
    return new JAXBElement<StartResponse>(_StartResponse_QNAME, StartResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Start }{@code >}}
   */
  @XmlElementDecl(namespace = "http://flows.jaxws.invoice.samples.integ.softfly.pl/", name = "start")
  public JAXBElement<Start> createStart(Start value) {
    return new JAXBElement<Start>(_Start_QNAME, Start.class, null, value);
  }

}
