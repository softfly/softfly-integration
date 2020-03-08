package pl.softfly.integ.doc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for processingLog complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="processingLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processingLog", propOrder = {
    "createdAt",
    "id",
    "msg",
    "source"
})
public class ProcessingLog {

  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar createdAt;
  protected Integer id;
  protected String msg;
  protected String source;

  /**
   * Gets the value of the createdAt property.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the value of the createdAt property.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setCreatedAt(XMLGregorianCalendar value) {
    this.createdAt = value;
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
   * Gets the value of the msg property.
   *
   * @return possible object is {@link String }
   */
  public String getMsg() {
    return msg;
  }

  /**
   * Sets the value of the msg property.
   *
   * @param value allowed object is {@link String }
   */
  public void setMsg(String value) {
    this.msg = value;
  }

  /**
   * Gets the value of the source property.
   *
   * @return possible object is {@link String }
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSource(String value) {
    this.source = value;
  }

}
