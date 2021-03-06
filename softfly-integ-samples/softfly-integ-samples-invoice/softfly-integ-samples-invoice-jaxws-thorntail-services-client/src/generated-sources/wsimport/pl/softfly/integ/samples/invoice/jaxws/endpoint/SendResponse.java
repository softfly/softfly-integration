package pl.softfly.integ.samples.invoice.jaxws.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * <p>Java class for sendResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="sendResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://softfly.pl/integ/shipment/entity}shipmentOutgoing" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendResponse", propOrder = {
    "_return"
})
public class SendResponse {

  @XmlElement(name = "return")
  protected ShipmentOutgoing _return;

  /**
   * Gets the value of the return property.
   *
   * @return possible object is {@link ShipmentOutgoing }
   */
  public ShipmentOutgoing getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   *
   * @param value allowed object is {@link ShipmentOutgoing }
   */
  public void setReturn(ShipmentOutgoing value) {
    this._return = value;
  }

}
