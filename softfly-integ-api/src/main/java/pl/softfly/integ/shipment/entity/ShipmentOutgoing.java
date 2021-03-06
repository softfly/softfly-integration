package pl.softfly.integ.shipment.entity;

/**
 * Entity.
 */
public class ShipmentOutgoing extends Shipment {

  private ShipmentOutgoingStatus status;

  /**
   * The cost of preparation for sending. Used to compare which endpoint to send is better to
   * choose.
   */
  private Integer cost;

  public ShipmentOutgoingStatus getStatus() {
    return status;
  }

  public void setStatus(ShipmentOutgoingStatus status) {
    this.status = status;
  }

  public Integer getCost() {
    return cost;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

}
