package pl.softfly.integ.samples.invoice.flows;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.softfly.integ.shipment.entity.ShipmentIncoming;
import pl.softfly.integ.shipment.in.IncomingShipment;
import pl.softfly.integ.shipment.in.MockedIncomingShipmentBean;

public class ForwardDocumentFlowBeanTest {

  protected IncomingShipment incomingShipment = new MockedIncomingShipmentBean();

  protected ForwardDocumentFlowBean flow = new ForwardDocumentFlowBean();

  @Test
  public void notRecognizedTest() {
    // Input
    String inputDocument = "NotRecognized";

    // Execute
    ShipmentIncoming shipment = incomingShipment.create(inputDocument);
    ForwardDocumentFlowResponse response = flow.start(shipment);

    // Result
    assertEquals("NOT_RECOGNIZED", response.getResult());
  }

  @Test
  public void schemeNotValidTest() {
    // Input
    String inputDocument = "SchemeNotValid";

    // Execute
    ShipmentIncoming shipment = incomingShipment.create(inputDocument);
    ForwardDocumentFlowResponse response = flow.start(shipment);

    // Result
    assertEquals("SCHEME_NOT_VALID", response.getResult());
  }

  @Test
  public void businessNotValidTest() {
    // Input
    String inputDocument = "BusinessNotValid";

    // Execute
    ShipmentIncoming shipment = incomingShipment.create(inputDocument);
    ForwardDocumentFlowResponse response = flow.start(shipment);

    // Result
    assertEquals("BUSINESS_NOT_VALID", response.getResult());
  }

  @Test
  public void happyFlowTest() {
    // Input
    String inputDocument = "HappyFlow";

    // Execute
    ShipmentIncoming shipment = incomingShipment.create(inputDocument);
    ForwardDocumentFlowResponse response = flow.start(shipment);

    // Result
    assertEquals("SUBMITTED", response.getResult());
  }

}
