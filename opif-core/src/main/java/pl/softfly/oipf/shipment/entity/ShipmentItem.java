package pl.softfly.oipf.shipment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import pl.softfly.oipf.entity.Endpoint;

@Entity
@XmlRootElement
@Table
public class ShipmentItem {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
	private Endpoint endpoint;

    @Column
    private ShipmentItemStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	public ShipmentItemStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentItemStatus status) {
		this.status = status;
	}

}
