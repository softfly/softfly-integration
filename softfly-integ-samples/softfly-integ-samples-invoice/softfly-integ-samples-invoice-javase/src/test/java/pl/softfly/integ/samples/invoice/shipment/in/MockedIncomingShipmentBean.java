package pl.softfly.integ.samples.invoice.shipment.in;

import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.samples.invoice.entity.InvoiceDocumentStatus;
import pl.softfly.integ.shipment.in.AbstractIncomingShipmentBean;

/**
 * {@inheritDoc}
 */
public class MockedIncomingShipmentBean extends AbstractIncomingShipmentBean {

  @Override
  protected DocumentHeader newDocumentHeader() {
    DocumentHeader documentHeader = super.newDocumentHeader();
    documentHeader.setStatus(InvoiceDocumentStatus.LOADED);
    return documentHeader;
  }

  @Override
  protected Endpoint newEndpoint() {
    Endpoint endpoint = super.newEndpoint();
    endpoint.setName("JunitTest");
    return endpoint;
  }

}
