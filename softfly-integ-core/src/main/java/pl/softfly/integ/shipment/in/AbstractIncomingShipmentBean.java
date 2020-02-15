package pl.softfly.integ.shipment.in;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.shipment.entity.ShipmentIncoming;
import pl.softfly.integ.shipment.entity.ShipmentIncomingStatus;

/**
 * Create {@link ShipmentIncoming} with details from where it came from.
 */
public abstract class AbstractIncomingShipmentBean implements IncomingShipment {

  @Override
  public ShipmentIncoming create(String inputText) {
    return newShipment(inputText);
  }

  protected ShipmentIncoming newShipment(String inputText) {
    ShipmentIncoming shipment = new ShipmentIncoming();

    shipment.setEndpoint(newEndpoint());
    shipment.setStatus(ShipmentIncomingStatus.RECEIVED);

    DocumentHeader documentHeader = newDocumentHeader();

    List<DocumentBody> bodies = new LinkedList<>();
    bodies.add(newDocumentBody(inputText));
    documentHeader.setBodies(bodies);

    shipment.setDocumentHeader(documentHeader);
    return shipment;
  }

  protected DocumentBody newDocumentBody(String inputText) {
    Objects.requireNonNull(inputText);
    DocumentBody documentBody = new DocumentBody();
    documentBody.setId(1);
    documentBody.setBody(inputText);
    documentBody.setVersion(0);
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
