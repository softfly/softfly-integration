package pl.softfly.integ.doc.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.Shipment;


/**
 * Entity.
 */
public class DocumentHeader implements Serializable, Cloneable {

  private Integer id;

  private Set<DocumentBusinessType> documentBusinessType;

  /**
   * Object because: com.sun.xml.bind.v2.runtime.IllegalAnnotationsException: 1 counts of
   * IllegalAnnotationExceptions pl.softfly.integ.doc.entity.DocumentStatus is an interface, and
   * JAXB can't handle interfaces.
   */
  private Object status;

  private Participant sender;

  private List<Participant> recipients = new LinkedList<>();

  private List<DocumentBody> bodies = new LinkedList<>();

  private List<Shipment> shipments;

  private Integer version;

  private List<ProcessingLog> processingLogs = new LinkedList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<DocumentBusinessType> getDocumentBusinessType() {
    return documentBusinessType;
  }

  public void setDocumentBusinessType(Set<DocumentBusinessType> documentBusinessType) {
    this.documentBusinessType = documentBusinessType;
  }

  public Object getStatus() {
    return status;
  }

  public void setStatus(DocumentStatus status) {
    this.status = status;
  }

  public Participant getSender() {
    return sender;
  }

  public void setSender(Participant sender) {
    this.sender = sender;
  }

  public List<Participant> getRecipients() {
    return recipients;
  }

  public void setRecipients(List<Participant> recipients) {
    this.recipients = recipients;
  }

  public List<DocumentBody> getBodies() {
    return bodies;
  }

  public void setBodies(List<DocumentBody> bodies) {
    this.bodies = bodies;
  }

  public List<Shipment> getShipments() {
    return shipments;
  }

  public void setShipments(List<Shipment> shipments) {
    this.shipments = shipments;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public List<ProcessingLog> getProcessingLogs() {
    return processingLogs;
  }

  public void setProcessingLogs(List<ProcessingLog> processingLogs) {
    this.processingLogs = processingLogs;
  }

  @Override
  public DocumentHeader clone() throws CloneNotSupportedException {
    return (DocumentHeader) super.clone();
  }
}
