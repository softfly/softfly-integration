package pl.softfly.oipf.endpoint.entity;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.Endpoint;

public class DocumentShipmentItem {

	private DocumentBody documentBody;

	private Endpoint endpoint;

	private DocumentShipmentItemStatus status = DocumentShipmentItemStatus.NONE;

	public DocumentShipmentItem(DocumentBody documentBody, Endpoint endpoint) {
		this.documentBody = documentBody;
		this.endpoint = endpoint;
	}

	public DocumentBody getDocumentBody() {
		return documentBody;
	}

	public void setDocumentBody(DocumentBody documentBody) {
		this.documentBody = documentBody;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	public DocumentShipmentItemStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentShipmentItemStatus status) {
		this.status = status;
	}
}
