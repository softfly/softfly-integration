package pl.softfly.integ.doc.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documentBody complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="documentBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentFormat" type="{http://softfly.pl/integ/doc/entity}documentFormat" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="processingLogs" type="{http://softfly.pl/integ/doc/entity}processingLog" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validations" type="{http://softfly.pl/integ/doc/entity}documentValidation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentBody", propOrder = {
    "body",
    "documentFormat",
    "id",
    "processingLogs",
    "validations",
    "version"
})
public class DocumentBody {

  protected String body;
  protected DocumentFormat documentFormat;
  protected Integer id;
  @XmlElement(nillable = true)
  protected List<ProcessingLog> processingLogs;
  @XmlElement(nillable = true)
  protected List<DocumentValidation> validations;
  protected Integer version;

  /**
   * Gets the value of the body property.
   *
   * @return possible object is {@link String }
   */
  public String getBody() {
    return body;
  }

  /**
   * Sets the value of the body property.
   *
   * @param value allowed object is {@link String }
   */
  public void setBody(String value) {
    this.body = value;
  }

  /**
   * Gets the value of the documentFormat property.
   *
   * @return possible object is {@link DocumentFormat }
   */
  public DocumentFormat getDocumentFormat() {
    return documentFormat;
  }

  /**
   * Sets the value of the documentFormat property.
   *
   * @param value allowed object is {@link DocumentFormat }
   */
  public void setDocumentFormat(DocumentFormat value) {
    this.documentFormat = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setId(Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the processingLogs property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the processingLogs property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getProcessingLogs().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link ProcessingLog }
   */
  public List<ProcessingLog> getProcessingLogs() {
    if (processingLogs == null) {
      processingLogs = new ArrayList<ProcessingLog>();
    }
    return this.processingLogs;
  }

  /**
   * Gets the value of the validations property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the validations property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getValidations().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocumentValidation }
   */
  public List<DocumentValidation> getValidations() {
    if (validations == null) {
      validations = new ArrayList<DocumentValidation>();
    }
    return this.validations;
  }

  /**
   * Gets the value of the version property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * Sets the value of the version property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setVersion(Integer value) {
    this.version = value;
  }

}
