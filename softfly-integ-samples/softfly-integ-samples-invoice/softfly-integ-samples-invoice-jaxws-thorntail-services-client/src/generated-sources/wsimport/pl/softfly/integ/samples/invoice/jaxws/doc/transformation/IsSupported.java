package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentFormat;


/**
 * <p>Java class for isSupported complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="isSupported">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://softfly.pl/integ/doc/entity}documentFormat" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://softfly.pl/integ/doc/entity}documentFormat" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isSupported", propOrder = {
    "arg0",
    "arg1"
})
public class IsSupported {

  protected DocumentFormat arg0;
  protected DocumentFormat arg1;

  /**
   * Gets the value of the arg0 property.
   *
   * @return possible object is {@link DocumentFormat }
   */
  public DocumentFormat getArg0() {
    return arg0;
  }

  /**
   * Sets the value of the arg0 property.
   *
   * @param value allowed object is {@link DocumentFormat }
   */
  public void setArg0(DocumentFormat value) {
    this.arg0 = value;
  }

  /**
   * Gets the value of the arg1 property.
   *
   * @return possible object is {@link DocumentFormat }
   */
  public DocumentFormat getArg1() {
    return arg1;
  }

  /**
   * Sets the value of the arg1 property.
   *
   * @param value allowed object is {@link DocumentFormat }
   */
  public void setArg1(DocumentFormat value) {
    this.arg1 = value;
  }

}
