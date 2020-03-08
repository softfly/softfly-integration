package pl.softfly.integ.samples.invoice.jaxws.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * <p>Java class for send complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="send">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://softfly.pl/integ/shipment/entity}shipmentOutgoing" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send", propOrder = {
    "arg0"
})
public class Send {

  protected ShipmentOutgoing arg0;

  /**
   * Gets the value of the arg0 property.
   *
   * @return possible object is {@link ShipmentOutgoing }
   */
  public ShipmentOutgoing getArg0() {
    return arg0;
  }

  /**
   * Sets the value of the arg0 property.
   *
   * @param value allowed object is {@link ShipmentOutgoing }
   */
  public void setArg0(ShipmentOutgoing value) {
    this.arg0 = value;
  }

}
