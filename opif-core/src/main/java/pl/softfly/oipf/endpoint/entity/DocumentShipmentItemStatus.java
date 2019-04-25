package pl.softfly.oipf.endpoint.entity;

public enum DocumentShipmentItemStatus {
	NONE(0, "NONE"),

	ERROR(-1, "ERROR"),

	AWAITING_SEND(100, "AWAITING_SEND"),
	SENT(200, "SENT"),
	AWAITING_ACK(300, "AWAITING_ACK"),
	COMITTED(400, "COMITTED");

	private Integer id;

	private String name;

	private DocumentShipmentItemStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
