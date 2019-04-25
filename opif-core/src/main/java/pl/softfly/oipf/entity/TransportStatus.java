package pl.softfly.oipf.entity;

public enum TransportStatus {
	ERROR(-1, "ERROR"),
	NONE(0, "NONE"),

	//1xxx Incoming
	AWAITING_RECEIVE(1100, "AWAITING_RECEIVE"),
	RECEIVING(1200, "RECEIVING"),
	RECEIVED(1200, "RECEIVED"),

	//2xxx Outgoing
	AWAITING_SEND(2100, "AWAITING_SEND"),
	SENT(2200, "SENT"),
	AWAITING_ACK(2300, "AWAITING_ACK"),
	COMITTED(2400, "COMITTED");

	private Integer id;

	private String name;

	private TransportStatus(Integer id, String name) {
		this.name = name;
	}
}
