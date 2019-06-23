package pl.softfly.integ.shipment.in;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.shipment.entity.Shipment;
import pl.softfly.integ.shipment.entity.ShipmentIncoming;
import pl.softfly.integ.shipment.entity.ShipmentIncomingStatus;

/**
 * Create {@link ShipmentIncoming} with details from where it came from.
 *
 * @author Grzegorz Ziemski
 */
public abstract class AbstractIncomingShipmentBean implements IncomingShipment {

  /**
   * {@inheritDoc}
   */
  public ShipmentIncoming create(String inputText) {
    return newShipment(inputText);
  }

  protected ShipmentIncoming newShipment(String inputText) {
    ShipmentIncoming shipment = new ShipmentIncoming();

    shipment.setDocumentBody(newDocumentBody(inputText));
    shipment.setEndpoint(newEndpoint());
    shipment.setStatus(ShipmentIncomingStatus.RECEIVED);

    List<Shipment> shipments = new LinkedList<>();
    shipments.add(shipment);
    shipment.getDocumentBody().getDocumentHeader().setShipments(shipments);
    return shipment;
  }

  protected DocumentBody newDocumentBody(String inputText) {
    Objects.requireNonNull(inputText);

    DocumentBody documentBody = new DocumentBody();
    documentBody.setBody(inputText);
    documentBody.setDocumentHeader(newDocumentHeader());
    documentBody.setVersion(0);

    List<DocumentBody> bodies = new LinkedList<>();
    bodies.add(documentBody);
    documentBody.getDocumentHeader().setBodies(bodies);

    return documentBody;
  }

  protected DocumentHeader newDocumentHeader() {
    DocumentHeader documentHeader = new DocumentHeader();
    documentHeader.setVersion(0);
    return documentHeader;
  }

  protected Endpoint newEndpoint() {
    return new Endpoint();
  }

}
