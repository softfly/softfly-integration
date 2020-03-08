package pl.softfly.integ.samples.invoice.jaxws.shipment.out;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.samples.invoice.jaxws.shipment.out package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Determine_QNAME = new QName(
      "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", "determine");
  private final static QName _DetermineResponse_QNAME = new QName(
      "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", "determineResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.samples.invoice.jaxws.shipment.out
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Determine }
   */
  public Determine createDetermine() {
    return new Determine();
  }

  /**
   * Create an instance of {@link DetermineResponse }
   */
  public DetermineResponse createDetermineResponse() {
    return new DetermineResponse();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Determine }{@code >}}
   */
  @XmlElementDecl(namespace = "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", name = "determine")
  public JAXBElement<Determine> createDetermine(Determine value) {
    return new JAXBElement<Determine>(_Determine_QNAME, Determine.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link DetermineResponse }{@code >}}
   */
  @XmlElementDecl(namespace = "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", name = "determineResponse")
  public JAXBElement<DetermineResponse> createDetermineResponse(DetermineResponse value) {
    return new JAXBElement<DetermineResponse>(_DetermineResponse_QNAME, DetermineResponse.class,
        null, value);
  }

}
