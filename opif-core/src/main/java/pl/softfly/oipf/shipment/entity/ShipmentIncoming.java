package pl.softfly.oipf.shipment.entity;

public class ShipmentIncoming extends Shipment {

	private ShipmentIncomingStatus status;

	public ShipmentIncomingStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentIncomingStatus status) {
		this.status = status;
	}

}
