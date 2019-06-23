package pl.softfly.integ.doc.entity;

import java.io.Serializable;

/**
 * Entity e.g. INVOICE
 *
 * @author Grzegorz Ziemski
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

}
