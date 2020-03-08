package pl.softfly.integ.shipment.entity;

import pl.softfly.integ.doc.entity.DocumentHeader;

/**
 * Entity.
 */
public class ShipmentIncoming extends Shipment {

  private DocumentHeader documentHeader;

  private ShipmentIncomingStatus status;

  public DocumentHeader getDocumentHeader() {
    return documentHeader;
  }

  public void setDocumentHeader(DocumentHeader documentHeader) {
    this.documentHeader = documentHeader;
  }

  public ShipmentIncomingStatus getStatus() {
    return status;
  }

  public void setStatus(ShipmentIncomingStatus status) {
    this.status = status;
  }
}
