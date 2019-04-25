package pl.softfly.oipf.shipment;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.shipment.entity.Shipment;
import pl.softfly.oipf.shipment.entity.ShipmentDirection;
import pl.softfly.oipf.shipment.entity.ShipmentItem;
import pl.softfly.oipf.shipment.entity.ShipmentItemStatus;

public class IncomingShipmentBean {

	public Shipment extract(String inputShipment) {
		return newShipment(inputShipment);
	}

	public Shipment newShipment(String inputShipment) {
		Shipment shipment = new Shipment();
		shipment.setDocumentHeader(newDocumentHeader(inputShipment));
		shipment.setDirection(ShipmentDirection.INCOMING);
		shipment.setSender(new Participant());
		shipment.setItems(newShipmentItems());
		return shipment;
	}

    public DocumentHeader newDocumentHeader(String inputDocument) {
    	Objects.requireNonNull(inputDocument);
        DocumentBody documentBody = createDocumentBody(inputDocument);

        return documentBody.getDocumentHeader();
    }

    public DocumentBody createDocumentBody(String inputDocument) {
    	Objects.requireNonNull(inputDocument);

        DocumentBody documentBody = new DocumentBody();
        documentBody.setBody(inputDocument);

        DocumentHeader header = new DocumentHeader();
        documentBody.setDocumentHeader(header);
        header.getBodies().add(documentBody);

        return documentBody;
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
