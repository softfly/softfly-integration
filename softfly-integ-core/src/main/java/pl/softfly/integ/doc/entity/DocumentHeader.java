package pl.softfly.integ.doc.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.Shipment;


/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentHeader implements Serializable {

  private Integer id;

  private Set<DocumentBusinessType> documentBusinessType;

  private DocumentStatus status;

  private Participant sender;

  private List<Participant> recipients;

  private List<DocumentBody> bodies;

  private List<Shipment> shipments;

  private Integer version;

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

  public DocumentStatus getStatus() {
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

}
