package pl.softfly.integ.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import pl.softfly.integ.endpoint.entity.Endpoint;


/**
 * Entity.
 */
public class Participant implements Serializable {

  private Integer id;

  private String name;

  private List<Endpoint> endpoints;

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

  public List<Endpoint> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(List<Endpoint> endpoints) {
    this.endpoints = endpoints;
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
    Participant other = (Participant) obj;
    return Objects.equals(id, other.id) && Objects.equals(name, other.name);
  }

}
