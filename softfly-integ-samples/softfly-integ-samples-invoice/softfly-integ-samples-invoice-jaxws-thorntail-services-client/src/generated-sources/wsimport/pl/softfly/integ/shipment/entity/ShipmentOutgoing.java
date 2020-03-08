package pl.softfly.integ.shipment.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shipmentOutgoing complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="shipmentOutgoing">
 *   &lt;complexContent>
 *     &lt;extension base="{http://softfly.pl/integ/shipment/entity}shipment">
 *       &lt;sequence>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="status" type="{http://softfly.pl/integ/shipment/entity}shipmentOutgoingStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shipmentOutgoing", propOrder = {
    "cost",
    "status"
})
public class ShipmentOutgoing
    extends Shipment {

  protected Integer cost;
  @XmlSchemaType(name = "string")
  protected ShipmentOutgoingStatus status;

  /**
   * Gets the value of the cost property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getCost() {
    return cost;
  }

  /**
   * Sets the value of the cost property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setCost(Integer value) {
    this.cost = value;
  }

  /**
   * Gets the value of the status property.
   *
   * @return possible object is {@link ShipmentOutgoingStatus }
   */
  public ShipmentOutgoingStatus getStatus() {
    return status;
  }

  /**
   * Sets the value of the status property.
   *
   * @param value allowed object is {@link ShipmentOutgoingStatus }
   */
  public void setStatus(ShipmentOutgoingStatus value) {
    this.status = value;
  }

}
