package pl.softfly.integ.doc.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity e.g. INVOICE
 */
public class DocumentBusinessType implements Serializable {

  private Integer id;

  private String name;

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

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
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
    DocumentBusinessType other = (DocumentBusinessType) obj;
    return Objects.equals(id, other.id) && Objects.equals(name, other.name);
  }
}
