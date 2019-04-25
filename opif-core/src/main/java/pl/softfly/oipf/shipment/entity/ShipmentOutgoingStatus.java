package pl.softfly.oipf.shipment.entity;

/**
 * DEV see {@link TransportStatus}
 * @author ziems
 */
public enum ShipmentOutgoingStatus {
	ERROR(-1, "ERROR"),
	NONE(0, "NONE"),

	//1xxx Incoming
	AWAITING_RECEIVE(1100, "AWAITING_RECEIVE"),
	RECEIVING(1200, "RECEIVING"),
	RECEIVED(1200, "RECEIVED");

	private Integer id;

	private String name;

	private ShipmentOutgoingStatus(Integer id, String name) {
		this.name = name;
	}

}
