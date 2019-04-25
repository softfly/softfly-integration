package pl.softfly.oipf.shipment.entity;

public enum ShipmentStatus {
	NONE(0, "NONE"),

	ERROR(-1, "ERROR"),

	AWAITING_SEND(100, "AWAITING_SEND"),
	PROCESSING(200, "PROCESSING"),
	SENT(300, "SENT");

	private Integer id;

	private String name;

	private ShipmentStatus(Integer id, String name) {
		this.name = name;
	}

}
