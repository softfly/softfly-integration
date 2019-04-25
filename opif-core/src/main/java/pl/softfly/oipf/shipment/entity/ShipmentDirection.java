package pl.softfly.oipf.shipment.entity;

public enum ShipmentDirection {
	INCOMING(0, "INCOMING"),
	OUTGOING(1, "OUTGOING");

	private Integer id;

	private String name;

	private ShipmentDirection(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
