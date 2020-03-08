package pl.softfly.integ.endpoint.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.entity.Participant;


/**
 * <p>Java class for endpoint complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="endpoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentFormat" type="{http://softfly.pl/integ/doc/entity}documentFormat" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="participant" type="{http://softfly.pl/integ/entity}participant" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "endpoint", propOrder = {
    "documentFormat",
    "id",
    "name",
    "participant"
})
public class Endpoint {

  protected DocumentFormat documentFormat;
  protected Integer id;
  protected String name;
  protected Participant participant;

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
   * Gets the value of the name property.
   *
   * @return possible object is {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value allowed object is {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the value of the participant property.
   *
   * @return possible object is {@link Participant }
   */
  public Participant getParticipant() {
    return participant;
  }

  /**
   * Sets the value of the participant property.
   *
   * @param value allowed object is {@link Participant }
   */
  public void setParticipant(Participant value) {
    this.participant = value;
  }

}
