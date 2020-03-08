package pl.softfly.integ.doc.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity.
 */
public class DocumentFormat implements Serializable {

  private Integer id;

  private String name;

  private DocumentBusinessType documentBusinessType;

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

  public DocumentBusinessType getDocumentBusinessType() {
    return documentBusinessType;
  }

  public void setDocumentBusinessType(DocumentBusinessType documentBusinessType) {
    this.documentBusinessType = documentBusinessType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentBusinessType, id, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DocumentFormat other = (DocumentFormat) obj;
    return Objects.equals(documentBusinessType, other.documentBusinessType)
        && Objects.equals(id, other.id) && Objects.equals(name, other.name);
  }
}
