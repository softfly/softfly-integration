package pl.softfly.integ.samples.invoice.jaxws.flows;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * <p>Java class for forwardDocumentFlowResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="forwardDocumentFlowResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentHeader" type="{http://softfly.pl/integ/doc/entity}documentHeader" minOccurs="0"/>
 *         &lt;element name="errors" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "forwardDocumentFlowResponse", propOrder = {
    "documentHeader",
    "errors",
    "result"
})
public class ForwardDocumentFlowResponse {

  protected DocumentHeader documentHeader;
  @XmlElement(nillable = true)
  protected List<Object> errors;
  protected String result;

  /**
   * Gets the value of the documentHeader property.
   *
   * @return possible object is {@link DocumentHeader }
   */
  public DocumentHeader getDocumentHeader() {
    return documentHeader;
  }

  /**
   * Sets the value of the documentHeader property.
   *
   * @param value allowed object is {@link DocumentHeader }
   */
  public void setDocumentHeader(DocumentHeader value) {
    this.documentHeader = value;
  }

  /**
   * Gets the value of the errors property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the errors property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getErrors().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Object }
   */
  public List<Object> getErrors() {
    if (errors == null) {
      errors = new ArrayList<Object>();
    }
    return this.errors;
  }

  /**
   * Gets the value of the result property.
   *
   * @return possible object is {@link String }
   */
  public String getResult() {
    return result;
  }

  /**
   * Sets the value of the result property.
   *
   * @param value allowed object is {@link String }
   */
  public void setResult(String value) {
    this.result = value;
  }

}
