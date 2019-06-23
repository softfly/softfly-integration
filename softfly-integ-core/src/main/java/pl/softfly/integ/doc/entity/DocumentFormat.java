package pl.softfly.integ.doc.entity;

import java.io.Serializable;

/**
 * Entity.
 *
 * @author Grzegorz Ziemski
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
  public String toString() {
    return "DocumentFormat [name=" + name + "]";
  }

}
