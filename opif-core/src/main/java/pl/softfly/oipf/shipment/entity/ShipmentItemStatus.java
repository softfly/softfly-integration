package pl.softfly.oipf.shipment.entity;

public enum ShipmentItemStatus {
	NONE(0, "NONE"),

	ERROR(-1, "ERROR"),

	AWAITING_SEND(100, "AWAITING_SEND"),
	SENT(300, "SENT"),
	AWAITING_ACK(400, "AWAITING_ACK"),
	COMITTED(500, "COMITTED");

	private Integer id;

	private String name;

	private ShipmentItemStatus(Integer id, String name) {
		this.name = name;
	}

}
