package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * <p>Java class for recognize complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recognize">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://softfly.pl/integ/doc/entity}documentHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recognize", propOrder = {
    "arg0"
})
public class Recognize {

  protected DocumentHeader arg0;

  /**
   * Gets the value of the arg0 property.
   *
   * @return possible object is {@link DocumentHeader }
   */
  public DocumentHeader getArg0() {
    return arg0;
  }

  /**
   * Sets the value of the arg0 property.
   *
   * @param value allowed object is {@link DocumentHeader }
   */
  public void setArg0(DocumentHeader value) {
    this.arg0 = value;
  }

}
