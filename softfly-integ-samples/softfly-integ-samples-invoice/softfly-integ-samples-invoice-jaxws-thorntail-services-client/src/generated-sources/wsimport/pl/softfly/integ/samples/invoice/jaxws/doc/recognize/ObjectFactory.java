package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.samples.invoice.jaxws.doc.recognize package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Recognize_QNAME = new QName(
      "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", "recognize");
  private final static QName _RecognizeResponse_QNAME = new QName(
      "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", "recognizeResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.samples.invoice.jaxws.doc.recognize
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Recognize }
   */
  public Recognize createRecognize() {
    return new Recognize();
  }

  /**
   * Create an instance of {@link RecognizeResponse }
   */
  public RecognizeResponse createRecognizeResponse() {
    return new RecognizeResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Recognize }{@code >}}
   */
  @XmlElementDecl(namespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "recognize")
  public JAXBElement<Recognize> createRecognize(Recognize value) {
    return new JAXBElement<Recognize>(_Recognize_QNAME, Recognize.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link RecognizeResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://recognize.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "recognizeResponse")
  public JAXBElement<RecognizeResponse> createRecognizeResponse(RecognizeResponse value) {
    return new JAXBElement<RecognizeResponse>(_RecognizeResponse_QNAME, RecognizeResponse.class,
        null, value);
  }

}
