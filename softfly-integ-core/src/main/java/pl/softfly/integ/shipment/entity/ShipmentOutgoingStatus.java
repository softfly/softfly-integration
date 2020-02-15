package pl.softfly.integ.shipment.entity;

/**
 * Entity.
 */
public enum ShipmentOutgoingStatus {
  ERROR(ShipmentStatusLiterals.ERROR_ID, ShipmentStatusLiterals.ERROR), //
  NONE(ShipmentStatusLiterals.NONE_ID, ShipmentStatusLiterals.NONE), //

  // 2xxx Outgoing
  AWAITING_SEND(ShipmentStatusLiterals.AWAITING_SEND_ID, ShipmentStatusLiterals.AWAITING_SEND), //
  SENT(ShipmentStatusLiterals.SENT_ID, ShipmentStatusLiterals.SENT), //
  AWAITING_ACK(ShipmentStatusLiterals.AWAITING_ACK_ID, ShipmentStatusLiterals.AWAITING_ACK), //
  COMITTED(ShipmentStatusLiterals.COMITTED_ID, ShipmentStatusLiterals.COMITTED);

  private Integer id;

  private String name;

  ShipmentOutgoingStatus(Integer id, String name) {
    this.name = name;
  }

}
