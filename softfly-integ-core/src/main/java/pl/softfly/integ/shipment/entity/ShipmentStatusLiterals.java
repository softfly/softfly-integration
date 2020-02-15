package pl.softfly.integ.shipment.entity;

/**
 * Entity.
 */
class ShipmentStatusLiterals {

  public static final String ERROR = "ERROR";
  public static final int ERROR_ID = -1;
  public static final String NONE = "NONE";
  public static final int NONE_ID = 0;
  // 1xxx Incoming
  public static final String AWAITING_RECEIVE = "AWAITING_RECEIVE";
  public static final int AWAITING_RECEIVE_ID = 1100;
  public static final String RECEIVING = "RECEIVING";
  public static final int RECEIVING_ID = 1200;
  public static final String RECEIVED = "RECEIVED";
  public static final int RECEIVED_ID = 1200;
  // 2xxx Outgoing
  public static final String AWAITING_SEND = "AWAITING_SEND";
  public static final int AWAITING_SEND_ID = 2100;
  public static final String SENT = "SENT";
  public static final int SENT_ID = 2200;
  public static final String AWAITING_ACK = "AWAITING_ACK";
  public static final int AWAITING_ACK_ID = 2300;
  public static final String COMITTED = "COMITTED";
  public static final int COMITTED_ID = 2400;

  private ShipmentStatusLiterals() {
  }

}
