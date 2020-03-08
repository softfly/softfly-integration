package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.samples.invoice.jaxws.doc.transformation package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Transform_QNAME = new QName(
      "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", "transform");
  private final static QName _IsSupportedResponse_QNAME = new QName(
      "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", "isSupportedResponse");
  private final static QName _IsSupported_QNAME = new QName(
      "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", "isSupported");
  private final static QName _TransformResponse_QNAME = new QName(
      "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", "transformResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.samples.invoice.jaxws.doc.transformation
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Transform }
   */
  public Transform createTransform() {
    return new Transform();
  }

  /**
   * Create an instance of {@link IsSupportedResponse }
   */
  public IsSupportedResponse createIsSupportedResponse() {
    return new IsSupportedResponse();
  }

  /**
   * Create an instance of {@link IsSupported }
   */
  public IsSupported createIsSupported() {
    return new IsSupported();
  }

  /**
   * Create an instance of {@link TransformResponse }
   */
  public TransformResponse createTransformResponse() {
    return new TransformResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Transform }{@code >}}
   */
  @XmlElementDecl(namespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "transform")
  public JAXBElement<Transform> createTransform(Transform value) {
    return new JAXBElement<Transform>(_Transform_QNAME, Transform.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link IsSupportedResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "isSupportedResponse")
  public JAXBElement<IsSupportedResponse> createIsSupportedResponse(IsSupportedResponse value) {
    return new JAXBElement<IsSupportedResponse>(_IsSupportedResponse_QNAME,
        IsSupportedResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link IsSupported }{@code >}}
   */
  @XmlElementDecl(namespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "isSupported")
  public JAXBElement<IsSupported> createIsSupported(IsSupported value) {
    return new JAXBElement<IsSupported>(_IsSupported_QNAME, IsSupported.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TransformResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://transformation.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "transformResponse")
  public JAXBElement<TransformResponse> createTransformResponse(TransformResponse value) {
    return new JAXBElement<TransformResponse>(_TransformResponse_QNAME, TransformResponse.class,
        null, value);
  }

}
