package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentBody;


/**
 * <p>Java class for transformResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="transformResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://softfly.pl/integ/doc/entity}documentBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transformResponse", propOrder = {
    "_return"
})
public class TransformResponse {

  @XmlElement(name = "return")
  protected DocumentBody _return;

  /**
   * Gets the value of the return property.
   *
   * @return possible object is {@link DocumentBody }
   */
  public DocumentBody getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   *
   * @param value allowed object is {@link DocumentBody }
   */
  public void setReturn(DocumentBody value) {
    this._return = value;
  }

}
