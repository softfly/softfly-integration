package pl.softfly.integ.samples.invoice.javase.flows;

import pl.softfly.integ.doc.parser.DocumentParserBean;
import pl.softfly.integ.doc.parser.LoggingDocumentParserBean;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormatBean;
import pl.softfly.integ.doc.recognize.LoggerDocumentRecognizeFormatBean;
import pl.softfly.integ.doc.transformation.DocumentTransformationBean;
import pl.softfly.integ.doc.transformation.LoggerDocumentTransformationBean;
import pl.softfly.integ.doc.validation.business.DocumentValidationBusinessBean;
import pl.softfly.integ.doc.validation.business.LoggerDocumentValidationBusinessBean;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchemaBean;
import pl.softfly.integ.doc.validation.schema.LoggerDocumentValidationSchemaBean;
import pl.softfly.integ.endpoint.LoggerSenderEndpointBean;
import pl.softfly.integ.endpoint.SenderEndpointBean;
import pl.softfly.integ.samples.invoice.flows.ForwardDocumentFlowBean;
import pl.softfly.integ.shipment.out.LoggerShipmentBean;
import pl.softfly.integ.shipment.out.TransformOutgoingShipmentBean;


public class JavaSEForwardDocumentFlowBean extends ForwardDocumentFlowBean {

  public JavaSEForwardDocumentFlowBean() {
    setDocumentRecognize(new LoggerDocumentRecognizeFormatBean(new DocumentRecognizeFormatBean()));
    setDocumentValidationSchema(
        new LoggerDocumentValidationSchemaBean(new DocumentValidationSchemaBean()));
    setDocumentValidationBusiness(
        new LoggerDocumentValidationBusinessBean(new DocumentValidationBusinessBean()));
    setDocumentParser(new LoggingDocumentParserBean(new DocumentParserBean()));
    setDocumentTransformation(
        new LoggerDocumentTransformationBean(new DocumentTransformationBean()));
    setOutgoingShipment(new LoggerShipmentBean(new TransformOutgoingShipmentBean()));
    setSenderEndpoint(new LoggerSenderEndpointBean(new SenderEndpointBean()));
  }

}
