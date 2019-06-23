package pl.softfly.integ.entity;

import java.io.Serializable;
import java.util.List;
import pl.softfly.integ.endpoint.entity.Endpoint;


/**
 * Entity.
 *
 * @author Grzegorz Ziemski
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

}
