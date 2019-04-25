package pl.softfly.oipf.shipment.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Participant;

@Entity
@XmlRootElement
@Table
public class Shipment {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private ShipmentDirection direction;

    @ManyToOne
    private DocumentHeader documentHeader;

    @ManyToOne
    private Participant sender;

    @OneToMany
    private Participant recipient;

    @ManyToOne
	private List<ShipmentItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShipmentDirection getDirection() {
		return direction;
	}

	public void setDirection(ShipmentDirection direction) {
		this.direction = direction;
	}

	public DocumentHeader getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	public Participant getSender() {
		return sender;
	}

	public void setSender(Participant sender) {
		this.sender = sender;
	}

	public Participant getRecipient() {
		return recipient;
	}

	public void setRecipient(Participant recipient) {
		this.recipient = recipient;
	}

	public List<ShipmentItem> getItems() {
		return items;
	}

	public void setItems(List<ShipmentItem> items) {
		this.items = items;
	}
}
