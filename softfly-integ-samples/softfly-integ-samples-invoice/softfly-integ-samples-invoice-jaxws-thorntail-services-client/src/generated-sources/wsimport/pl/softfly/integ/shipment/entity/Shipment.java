package pl.softfly.integ.shipment.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.endpoint.entity.Endpoint;


/**
 * <p>Java class for shipment complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="shipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentBody" type="{http://softfly.pl/integ/doc/entity}documentBody" minOccurs="0"/>
 *         &lt;element name="endpoint" type="{http://softfly.pl/integ/endpoint/entity}endpoint" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shipment", propOrder = {
    "documentBody",
    "endpoint",
    "id"
})
@XmlSeeAlso({
    ShipmentOutgoing.class
})
public abstract class Shipment {

  protected DocumentBody documentBody;
  protected Endpoint endpoint;
  protected Integer id;

  /**
   * Gets the value of the documentBody property.
   *
   * @return possible object is {@link DocumentBody }
   */
  public DocumentBody getDocumentBody() {
    return documentBody;
  }

  /**
   * Sets the value of the documentBody property.
   *
   * @param value allowed object is {@link DocumentBody }
   */
  public void setDocumentBody(DocumentBody value) {
    this.documentBody = value;
  }

  /**
   * Gets the value of the endpoint property.
   *
   * @return possible object is {@link Endpoint }
   */
  public Endpoint getEndpoint() {
    return endpoint;
  }

  /**
   * Sets the value of the endpoint property.
   *
   * @param value allowed object is {@link Endpoint }
   */
  public void setEndpoint(Endpoint value) {
    this.endpoint = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setId(Integer value) {
    this.id = value;
  }

}
