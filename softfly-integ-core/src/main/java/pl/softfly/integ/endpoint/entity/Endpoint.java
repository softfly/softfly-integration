package pl.softfly.integ.endpoint.entity;

import java.io.Serializable;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.entity.Participant;


/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public class Endpoint implements Serializable {

  private Integer id;

  private String name;

  private Participant participant;

  private DocumentFormat documentFormat;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }

  public DocumentFormat getDocumentFormat() {
    return documentFormat;
  }

  public void setDocumentFormat(DocumentFormat documentFormat) {
    this.documentFormat = documentFormat;
  }

  @Override
  public String toString() {
    return "Endpoint [documentFormat=" + documentFormat + "]";
  }

}
