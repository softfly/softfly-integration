package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * <p>Java class for recognizeResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recognizeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://softfly.pl/integ/doc/entity}documentHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recognizeResponse", propOrder = {
    "_return"
})
public class RecognizeResponse {

  @XmlElement(name = "return")
  protected DocumentHeader _return;

  /**
   * Gets the value of the return property.
   *
   * @return possible object is {@link DocumentHeader }
   */
  public DocumentHeader getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   *
   * @param value allowed object is {@link DocumentHeader }
   */
  public void setReturn(DocumentHeader value) {
    this._return = value;
  }

}
