package pl.softfly.integ.shipment.entity;

/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public enum ShipmentIncomingStatus {
  ERROR(ShipmentStatusLiterals.ERROR_ID, ShipmentStatusLiterals.ERROR), //
  NONE(ShipmentStatusLiterals.NONE_ID, ShipmentStatusLiterals.NONE),

  // 1xxx Incoming
  AWAITING_RECEIVE(ShipmentStatusLiterals.AWAITING_RECEIVE_ID,
      ShipmentStatusLiterals.AWAITING_RECEIVE), //
  RECEIVING(ShipmentStatusLiterals.RECEIVING_ID, ShipmentStatusLiterals.RECEIVING), //
  RECEIVED(ShipmentStatusLiterals.RECEIVED_ID, ShipmentStatusLiterals.RECEIVED);

  private Integer id;

  private String name;

  ShipmentIncomingStatus(Integer id, String name) {
    this.name = name;
  }

}
