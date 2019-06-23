package pl.softfly.integ.shipment.entity;

/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public class ShipmentIncoming extends Shipment {

  private ShipmentIncomingStatus status;

  public ShipmentIncomingStatus getStatus() {
    return status;
  }

  public void setStatus(ShipmentIncomingStatus status) {
    this.status = status;
  }

}
