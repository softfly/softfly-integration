package pl.softfly.oipf.shipment;

import java.util.LinkedList;
import java.util.List;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.shipment.entity.Shipment;
import pl.softfly.oipf.shipment.entity.ShipmentDirection;
import pl.softfly.oipf.shipment.entity.ShipmentItem;
import pl.softfly.oipf.shipment.entity.ShipmentItemStatus;

/**
 * Determine:
 * 	- recipients - outgoing participants,
 *  - endpoints to which a message can be sent.
 *
 * @author Grzegorz Ziemski <ziemski@gmail.com>
 */
public class OutgoingShipmentBean {

	public Shipment determine(DocumentHeader documentHeader) {
		return newShipment(documentHeader);
	}

	public Shipment newShipment(DocumentHeader documentHeader) {
		Shipment shipment = new Shipment();
		shipment.setDocumentHeader(documentHeader);
		shipment.setDirection(ShipmentDirection.OUTGOING);
		shipment.setRecipient(new Participant());
		shipment.setItems(newShipmentItems());
		return shipment;
	}

	protected List<ShipmentItem> newShipmentItems() {
		List<ShipmentItem> list = new LinkedList<ShipmentItem>();
		list.add(newShipmentItemAwaitingSend());
		list.add(newShipmentItemNone());
		return list;
	}

	protected ShipmentItem newShipmentItemAwaitingSend() {
		ShipmentItem item = new ShipmentItem();
		item.setStatus(ShipmentItemStatus.AWAITING_SEND);
		item.setEndpoint(newEndpoint());
		return item;
	}

	protected ShipmentItem newShipmentItemNone() {
		ShipmentItem item = new ShipmentItem();
		item.setStatus(ShipmentItemStatus.NONE);
		item.setEndpoint(newEndpoint());
		return item;
	}

	protected Endpoint newEndpoint() {
        Endpoint e = new Endpoint();
        e.setDictDocumentFormat(newDictDocumentFormat());
        return e;
    }

    protected DictDocumentFormat newDictDocumentFormat() {
        DictDocumentFormat documentFormat = new DictDocumentFormat();
        documentFormat.setName("G2_INVOICE_3.0");
        return documentFormat;
    }

}
