package pl.softfly.integ.samples.invoice.jaxws.flows;

import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.parser.LoggingDocumentParserBean;
import pl.softfly.integ.doc.recognize.LoggerDocumentRecognizeFormatBean;
import pl.softfly.integ.doc.transformation.LoggerDocumentTransformationBean;
import pl.softfly.integ.doc.validation.business.LoggerDocumentValidationBusinessBean;
import pl.softfly.integ.doc.validation.schema.LoggerDocumentValidationSchemaBean;
import pl.softfly.integ.endpoint.LoggerSenderEndpointBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.samples.invoice.flows.ForwardDocumentFlowBean;
import pl.softfly.integ.samples.invoice.flows.ForwardDocumentFlowResponse;
import pl.softfly.integ.samples.invoice.jaxws.doc.parser.DocumentParserBean;
import pl.softfly.integ.samples.invoice.jaxws.doc.recognize.DocumentRecognizeFormatBean;
import pl.softfly.integ.samples.invoice.jaxws.doc.transformation.DocumentTransformationBean;
import pl.softfly.integ.samples.invoice.jaxws.doc.validation.business.DocumentValidationBusinessBean;
import pl.softfly.integ.samples.invoice.jaxws.doc.validation.schema.DocumentValidationSchemaBean;
import pl.softfly.integ.samples.invoice.jaxws.endpoint.SenderEndpointBean;
import pl.softfly.integ.samples.invoice.jaxws.shipment.out.OutgoingShipmentBean;
import pl.softfly.integ.shipment.entity.ShipmentIncoming;
import pl.softfly.integ.shipment.in.AbstractIncomingShipmentBean;
import pl.softfly.integ.shipment.in.IncomingShipment;
import pl.softfly.integ.shipment.out.LoggerShipmentBean;

@WebService(serviceName = "ForwardDocumentFlow")
public class ForwardDocumentFlowService extends ForwardDocumentFlowBean {

  private IncomingShipment incomingShipment = new AbstractIncomingShipmentBean() {

    @Override
    protected DocumentHeader newDocumentHeader() {
      DocumentHeader documentHeader = super.newDocumentHeader();
      //documentHeader.setStatus(InvoiceDocumentStatus.LOADED);
      return documentHeader;
    }

    @Override
    protected Endpoint newEndpoint() {
      Endpoint endpoint = super.newEndpoint();
      endpoint.setName(ForwardDocumentFlowService.class.toString());
      return endpoint;
    }

  };

  public ForwardDocumentFlowService() {
    setDocumentRecognize(new LoggerDocumentRecognizeFormatBean(new DocumentRecognizeFormatBean()));
    setDocumentValidationSchema(
        new LoggerDocumentValidationSchemaBean(new DocumentValidationSchemaBean()));
    setDocumentValidationBusiness(
        new LoggerDocumentValidationBusinessBean(new DocumentValidationBusinessBean()));
    setDocumentParser(new LoggingDocumentParserBean(new DocumentParserBean()));
    setDocumentTransformation(
        new LoggerDocumentTransformationBean(new DocumentTransformationBean()));
    setOutgoingShipment(new LoggerShipmentBean(new OutgoingShipmentBean()));
    setSenderEndpoint(new LoggerSenderEndpointBean(new SenderEndpointBean()));
  }

  @WebMethod
  public ForwardDocumentFlowResponse start(String inputDocument) {
    ShipmentIncoming shipment = incomingShipment.create(inputDocument);
    return super.start(shipment);
  }

}
