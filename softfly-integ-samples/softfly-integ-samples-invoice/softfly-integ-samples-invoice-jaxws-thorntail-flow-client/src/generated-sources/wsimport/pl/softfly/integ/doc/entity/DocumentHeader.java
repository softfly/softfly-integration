package pl.softfly.integ.doc.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.Shipment;


/**
 * <p>Java class for documentHeader complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="documentHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bodies" type="{http://softfly.pl/integ/doc/entity}documentBody" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentBusinessType" type="{http://softfly.pl/integ/doc/entity}documentBusinessType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="processingLogs" type="{http://softfly.pl/integ/doc/entity}processingLog" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recipients" type="{http://softfly.pl/integ/entity}participant" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sender" type="{http://softfly.pl/integ/entity}participant" minOccurs="0"/>
 *         &lt;element name="shipments" type="{http://softfly.pl/integ/shipment/entity}shipment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentHeader", propOrder = {
    "bodies",
    "documentBusinessType",
    "id",
    "processingLogs",
    "recipients",
    "sender",
    "shipments",
    "version"
})
public class DocumentHeader {

  @XmlElement(nillable = true)
  protected List<DocumentBody> bodies;
  @XmlElement(nillable = true)
  protected List<DocumentBusinessType> documentBusinessType;
  protected Integer id;
  @XmlElement(nillable = true)
  protected List<ProcessingLog> processingLogs;
  @XmlElement(nillable = true)
  protected List<Participant> recipients;
  protected Participant sender;
  @XmlElement(nillable = true)
  protected List<Shipment> shipments;
  protected Integer version;

  /**
   * Gets the value of the bodies property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the bodies property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getBodies().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocumentBody }
   */
  public List<DocumentBody> getBodies() {
    if (bodies == null) {
      bodies = new ArrayList<DocumentBody>();
    }
    return this.bodies;
  }

  /**
   * Gets the value of the documentBusinessType property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the documentBusinessType property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getDocumentBusinessType().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocumentBusinessType }
   */
  public List<DocumentBusinessType> getDocumentBusinessType() {
    if (documentBusinessType == null) {
      documentBusinessType = new ArrayList<DocumentBusinessType>();
    }
    return this.documentBusinessType;
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
   * Gets the value of the recipients property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the recipients property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRecipients().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Participant }
   */
  public List<Participant> getRecipients() {
    if (recipients == null) {
      recipients = new ArrayList<Participant>();
    }
    return this.recipients;
  }

  /**
   * Gets the value of the sender property.
   *
   * @return possible object is {@link Participant }
   */
  public Participant getSender() {
    return sender;
  }

  /**
   * Sets the value of the sender property.
   *
   * @param value allowed object is {@link Participant }
   */
  public void setSender(Participant value) {
    this.sender = value;
  }

  /**
   * Gets the value of the shipments property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the shipments property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getShipments().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Shipment }
   */
  public List<Shipment> getShipments() {
    if (shipments == null) {
      shipments = new ArrayList<Shipment>();
    }
    return this.shipments;
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
