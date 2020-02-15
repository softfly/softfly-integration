package pl.softfly.integ.endpoint.entity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.entity.Participant;


/**
 * Entity.
 */
public class Endpoint implements Serializable {

  private Integer id;

  private String name;

  private Participant participant;

  private DocumentFormat documentFormat;

  public Endpoint() {

  }

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Endpoint endpoint = (Endpoint) o;

    return new EqualsBuilder().append(id, endpoint.id).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }
}
