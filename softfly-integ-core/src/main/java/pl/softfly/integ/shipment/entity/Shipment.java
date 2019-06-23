package pl.softfly.integ.shipment.entity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.endpoint.entity.Endpoint;

/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public abstract class Shipment implements Serializable {

  private Integer id;

  private DocumentBody documentBody;

  private Endpoint endpoint;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DocumentBody getDocumentBody() {
    return documentBody;
  }

  public void setDocumentBody(DocumentBody documentBody) {
    this.documentBody = documentBody;
  }

  public Endpoint getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(Endpoint endpoint) {
    this.endpoint = endpoint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Shipment shipment = (Shipment) o;

    return new EqualsBuilder().append(id, shipment.id).append(documentBody, shipment.documentBody)
        .append(endpoint, shipment.endpoint).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(documentBody).append(endpoint)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "Shipment [id=" + id + ", documentBody=" + documentBody + ", endpoint=" + endpoint + "]";
  }

}
