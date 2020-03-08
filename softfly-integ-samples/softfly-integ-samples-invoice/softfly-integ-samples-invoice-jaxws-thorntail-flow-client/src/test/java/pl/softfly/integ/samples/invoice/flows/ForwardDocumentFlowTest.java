package pl.softfly.integ.samples.invoice.flows;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import pl.softfly.integ.samples.invoice.jaxws.flows.ForwardDocumentFlow;
import pl.softfly.integ.samples.invoice.jaxws.flows.ForwardDocumentFlowResponse;
import pl.softfly.integ.samples.invoice.jaxws.flows.ForwardDocumentFlowService;

@Ignore
public class ForwardDocumentFlowTest {

  private ForwardDocumentFlow forwardDocumentFlow = new ForwardDocumentFlow();

  private ForwardDocumentFlowService forwardDocumentFlowService =
      forwardDocumentFlow.getForwardDocumentFlowServicePort();

  @Test
  public void notRecognizedTest() {
    // Input
    String inputDocument = "NotRecognized";
    // Execute
    ForwardDocumentFlowResponse response = forwardDocumentFlowService.start(inputDocument);
    // Result
    assertEquals("NOT_RECOGNIZED", response.getResult());
  }

  @Test
  public void schemeNotValidTest() {
    // Input
    String inputDocument = "SchemeNotValid";
    // Execute
    ForwardDocumentFlowResponse response = forwardDocumentFlowService.start(inputDocument);
    // Result
    assertEquals("SCHEME_NOT_VALID", response.getResult());
  }

  @Test
  public void businessNotValidTest() {
    // Input
    String inputDocument = "BusinessNotValid";
    // Execute
    ForwardDocumentFlowResponse response = forwardDocumentFlowService.start(inputDocument);
    // Result
    assertEquals("BUSINESS_NOT_VALID", response.getResult());
  }

  @Test
  public void happyFlowTest() {
    // Input
    String inputDocument = "HappyFlow";
    // Execute
    ForwardDocumentFlowResponse response = forwardDocumentFlowService.start(inputDocument);
    // Result
    assertEquals("SUBMITTED", response.getResult());
  }

}
