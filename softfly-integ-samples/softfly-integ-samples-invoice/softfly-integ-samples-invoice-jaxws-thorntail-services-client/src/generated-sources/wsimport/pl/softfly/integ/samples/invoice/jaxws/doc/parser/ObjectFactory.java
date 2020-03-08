package pl.softfly.integ.samples.invoice.jaxws.doc.parser;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.samples.invoice.jaxws.doc.parser package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _GetSupportedResponse_QNAME = new QName(
      "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", "getSupportedResponse");
  private final static QName _ParseResponse_QNAME = new QName(
      "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", "parseResponse");
  private final static QName _Parse_QNAME = new QName(
      "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", "parse");
  private final static QName _GetSupported_QNAME = new QName(
      "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", "getSupported");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.samples.invoice.jaxws.doc.parser
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link GetSupported }
   */
  public GetSupported createGetSupported() {
    return new GetSupported();
  }

  /**
   * Create an instance of {@link Parse }
   */
  public Parse createParse() {
    return new Parse();
  }

  /**
   * Create an instance of {@link ParseResponse }
   */
  public ParseResponse createParseResponse() {
    return new ParseResponse();
  }

  /**
   * Create an instance of {@link GetSupportedResponse }
   */
  public GetSupportedResponse createGetSupportedResponse() {
    return new GetSupportedResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetSupportedResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "getSupportedResponse")
  public JAXBElement<GetSupportedResponse> createGetSupportedResponse(GetSupportedResponse value) {
    return new JAXBElement<GetSupportedResponse>(_GetSupportedResponse_QNAME,
        GetSupportedResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ParseResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "parseResponse")
  public JAXBElement<ParseResponse> createParseResponse(ParseResponse value) {
    return new JAXBElement<ParseResponse>(_ParseResponse_QNAME, ParseResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Parse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "parse")
  public JAXBElement<Parse> createParse(Parse value) {
    return new JAXBElement<Parse>(_Parse_QNAME, Parse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link GetSupported }{@code >}}
   */
  @XmlElementDecl(namespace = "http://parser.doc.jaxws.invoice.samples.integ.softfly.pl/", name = "getSupported")
  public JAXBElement<GetSupported> createGetSupported(GetSupported value) {
    return new JAXBElement<GetSupported>(_GetSupported_QNAME, GetSupported.class, null, value);
  }

}
