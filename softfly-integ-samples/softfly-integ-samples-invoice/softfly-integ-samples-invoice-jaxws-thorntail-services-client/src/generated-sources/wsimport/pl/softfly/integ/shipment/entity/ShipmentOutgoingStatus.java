package pl.softfly.integ.shipment.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shipmentOutgoingStatus.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="shipmentOutgoingStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="AWAITING_SEND"/>
 *     &lt;enumeration value="SENT"/>
 *     &lt;enumeration value="AWAITING_ACK"/>
 *     &lt;enumeration value="COMITTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "shipmentOutgoingStatus")
@XmlEnum
public enum ShipmentOutgoingStatus {

  ERROR,
  NONE,
  AWAITING_SEND,
  SENT,
  AWAITING_ACK,
  COMITTED;

  public static ShipmentOutgoingStatus fromValue(String v) {
    return valueOf(v);
  }

  public String value() {
    return name();
  }

}
