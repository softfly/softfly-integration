package pl.softfly.integ.samples.invoice.jaxws.flows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for startResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="startResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://flows.jaxws.invoice.samples.integ.softfly.pl/}forwardDocumentFlowResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startResponse", propOrder = {
    "_return"
})
public class StartResponse {

  @XmlElement(name = "return")
  protected ForwardDocumentFlowResponse _return;

  /**
   * Gets the value of the return property.
   *
   * @return possible object is {@link ForwardDocumentFlowResponse }
   */
  public ForwardDocumentFlowResponse getReturn() {
    return _return;
  }

  /**
   * Sets the value of the return property.
   *
   * @param value allowed object is {@link ForwardDocumentFlowResponse }
   */
  public void setReturn(ForwardDocumentFlowResponse value) {
    this._return = value;
  }

}
