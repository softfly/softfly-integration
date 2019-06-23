package pl.softfly.integ.samples.invoice.flows;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.parser.DocumentParser;
import pl.softfly.integ.doc.parser.DocumentParserBean;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormat;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormatBean;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormatUtil;
import pl.softfly.integ.doc.transformation.DocumentTransformation;
import pl.softfly.integ.doc.transformation.DocumentTransformationBean;
import pl.softfly.integ.doc.validation.business.DocumentValidationBusiness;
import pl.softfly.integ.doc.validation.business.DocumentValidationBusinessBean;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchema;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchemaBean;
import pl.softfly.integ.endpoint.SenderEndpoint;
import pl.softfly.integ.endpoint.SenderEndpointBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.samples.invoice.entity.InvoiceDocumentStatus;
import pl.softfly.integ.shipment.entity.Shipment;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.out.OutgoingShipment;
import pl.softfly.integ.shipment.out.TransformOutgoingShipmentBean;


/**
 * The example flow that transmits an invoice from the seller to the buyer.
 *
 * <p>
 * <table border="1">
 * <caption>Business flow</caption>
 * <tr>
 * <td>#</td>
 * <td>Receive the document from the endpoint.</td>
 * <td>{@link pl.softfly.integ.shipment.in.IncomingShipment}</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>Recognize the document format.</td>
 * <td>{@link DocumentRecognizeFormat}</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>Validate schema of the document.</td>
 * <td>{@link DocumentValidationSchema}</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>(Optional) Transform to the supported document format by DocumentValidationBusiness.</td>
 * <td>{@link DocumentTransformation}</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>Validate business validation of the document.</td>
 * <td>{@link DocumentValidationBusiness}</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>Parse the document to POJO (recipients).</td>
 * <td>{@link DocumentParser}</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>Other business steps. (Changes in the content of the document.)</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>Determine the shipment (endpoints, recipient) to which the document should be sent.</td>
 * <td>{@link OutgoingShipment }</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>Generate the document from POJO.</td>
 * <td>{@link DocumentTransformation }</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>(Optional) Transform to the recipient's document format.</td>
 * <td>{@link DocumentTransformation }</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>Send the document.</td>
 * <td>{@link SenderEndpoint }</td>
 * </tr>
 * </table>
 *
 * @author Grzegorz Ziemski
 */
public class ForwardDocumentFlowBean {

  protected static final int LIMIT_TRY_DETERMINE_SHIPMENT = 10;

  private DocumentRecognizeFormat documentRecognize = new DocumentRecognizeFormatBean();

  private DocumentValidationSchema documentValidationSchema = new DocumentValidationSchemaBean();

  private DocumentValidationBusiness documentValidationBusiness =
      new DocumentValidationBusinessBean();

  private DocumentParser documentParser = new DocumentParserBean();

  private DocumentTransformation documentTransformation = new DocumentTransformationBean();

  private OutgoingShipment outgoingShipment = new TransformOutgoingShipmentBean();

  private SenderEndpoint senderEndpoint = new SenderEndpointBean();

  public ForwardDocumentFlowResponse start(Shipment shipmentIncoming) {
    ForwardDocumentFlowResponse response = new ForwardDocumentFlowResponse();

    // Receive the document from the endpoint.
    DocumentHeader documentHeader = shipmentIncoming.getDocumentBody().getDocumentHeader();

    // 1. Recognize the document format.
    if (!recognizeDocument(documentHeader)) {
      response.setResult(documentHeader.getStatus().toString());
      return response;
    }

    // 2. Validate schema of the document.
    List<?> errors = validateDocumentSchema(documentHeader);
    if (CollectionUtils.isNotEmpty(errors)) {
      response.setResult(documentHeader.getStatus().toString());
      response.setErrors(errors);
      return response;
    }

    // 3. (Optional) Transform to the supported document format by DocumentValidationBusiness.
    errors = transformBeforeValidateDocumentBusiness(documentHeader);
    if (CollectionUtils.isNotEmpty(errors)) {
      response.setResult(documentHeader.getStatus().toString());
      response.setErrors(errors);
      return response;
    }

    // 4. Validate business validation of the document.
    errors = validateDocumentBusiness(documentHeader);
    if (CollectionUtils.isNotEmpty(errors)) {
      response.setResult(documentHeader.getStatus().toString());
      response.setErrors(errors);
      return response;
    }

    // 5. Parse the document to POJO (recipients).
    parseDocument(documentHeader);

    // Other business steps. (Changes in the content of the document.)

    for (int i = 0;; i++) {
      if (i > LIMIT_TRY_DETERMINE_SHIPMENT) {
        throw new RuntimeException("LIMIT_TRY_DETERMINE_SHIPMENT");
      } else if (getOutgoingShipment().isRequired(documentHeader)) {
        // 6. Determine the shipment (endpoints, recipient) to which the document should be sent.
        Collection<ShipmentOutgoing> shipments = outgoingShipment.determine(documentHeader);
        documentHeader.getShipments().addAll(shipments);

        for (ShipmentOutgoing shipment : shipments) {
          DocumentBody orginBody = shipment.getDocumentBody();
          Endpoint endpoint = shipment.getEndpoint();

          // 7. Generate the document from POJO.
          // 8. (Optional) Transform to the recipient's document format.
          if (!orginBody.getDocumentFormat().equals(endpoint.getDocumentFormat())) {
            shipment.setDocumentBody(transformDocument(orginBody, endpoint.getDocumentFormat()));
          }

          // 9. Send document.
          sendDocument(shipment);
        }
      } else {
        break;
      }
    }

    response.setResult(documentHeader.getStatus().toString());
    return response;
  }

  /**
   * 1. Recognize the document format.
   */
  protected boolean recognizeDocument(DocumentHeader header) {
    boolean isRecognize =
        DocumentRecognizeFormatUtil.enrichRecognize(getDocumentRecognize(), header);
    header.setStatus(
        isRecognize ? InvoiceDocumentStatus.RECOGNIZED : InvoiceDocumentStatus.NOT_RECOGNIZED);
    return isRecognize;
  }

  /**
   * 2. Validate schema of the document.
   */
  protected List<?> validateDocumentSchema(DocumentHeader header) {
    List<?> list = getDocumentValidationSchema().validate(header);
    boolean isValid = CollectionUtils.isEmpty(list);
    header.setStatus(
        isValid ? InvoiceDocumentStatus.SCHEME_VALID : InvoiceDocumentStatus.SCHEME_NOT_VALID);
    return list;
  }

  /**
   * 3. (Optional) Transform to the supported document format by
   * {@link pl.softfly.integ.doc.validation.business.DocumentValidationBusiness}.
   *
   * <p>
   * 3.1. Check whether transformation is necessary.
   *
   * <p>
   * 3.2. Transform to the supported document format by
   * {@link pl.softfly.integ.doc.validation.business.DocumentValidationBusiness}.
   *
   * <p>
   * 3.2.E Error, It is not possible to transform into a document format supported by
   * {@link pl.softfly.integ.doc.validation.business.DocumentValidationBusiness}.
   */
  protected List<?> transformBeforeValidateDocumentBusiness(DocumentHeader documentHeader) {
    BUSINESS_TYPE: for (DocumentBusinessType businessType : documentHeader
        .getDocumentBusinessType()) {
      List<DocumentFormat> documentFormats =
          getDocumentValidationBusiness().getSupported(businessType);

      // 3.1.
      for (DocumentBody body : documentHeader.getBodies()) {
        if (documentFormats.contains(body.getDocumentFormat())) {
          continue BUSINESS_TYPE;
        }
      }

      // 3.2.
      for (DocumentBody body : documentHeader.getBodies()) {
        for (DocumentFormat supportedDocumentFormats : documentFormats) {
          if (getDocumentTransformation().isSupported(body.getDocumentFormat(),
              supportedDocumentFormats)) {
            transformDocument(body, supportedDocumentFormats);
            continue BUSINESS_TYPE;
          }
        }
      }

      // 3.2.E.
      documentHeader.setStatus(InvoiceDocumentStatus.BUSINESS_NOT_VALID);
      List<String> errors = new LinkedList<>();
      errors.add(
          "ERROR: It is not possible to transform into a document format supported by DocumentValidationBusiness.");
      return errors;
    }
    return null;
  }

  /**
   * 4. Validate business validation of the document.
   */
  protected List<?> validateDocumentBusiness(DocumentHeader documentHeader) {
    List<?> list = getDocumentValidationBusiness().validate(documentHeader);
    boolean isValid = list == null || list.isEmpty();
    documentHeader.setStatus(
        isValid ? InvoiceDocumentStatus.BUSINESS_VALID : InvoiceDocumentStatus.BUSINESS_NOT_VALID);
    return list;
  }

  /**
   * 5. Parse the document to POJO (recipients).
   */
  protected void parseDocument(DocumentHeader documentHeader) {
    documentHeader.getBodies().add(getDocumentParser().parse(documentHeader));
    documentHeader.setStatus(InvoiceDocumentStatus.PARSED);
  }

  protected DocumentBody transformDocument(DocumentBody documentBody,
      DocumentFormat targetDocumentFormat) {
    if (documentBody.getDocumentFormat().equals(targetDocumentFormat)) {
      return documentBody;
    } else {
      DocumentBody newBody =
          getDocumentTransformation().transform(documentBody, targetDocumentFormat);
      documentBody.getDocumentHeader().getBodies().add(newBody);
      return newBody;
    }
  }

  protected boolean sendDocument(ShipmentOutgoing shipment) {
    if (getSenderEndpoint().send(shipment)) {
      shipment.getDocumentBody().getDocumentHeader().setStatus(InvoiceDocumentStatus.SUBMITTED);
      return true;
    } else {
      return false;
    }
  }

  public DocumentRecognizeFormat getDocumentRecognize() {
    return documentRecognize;
  }

  public void setDocumentRecognize(DocumentRecognizeFormat documentRecognize) {
    this.documentRecognize = documentRecognize;
  }

  public DocumentValidationSchema getDocumentValidationSchema() {
    return documentValidationSchema;
  }

  public void setDocumentValidationSchema(DocumentValidationSchema documentValidationSchema) {
    this.documentValidationSchema = documentValidationSchema;
  }

  public DocumentValidationBusiness getDocumentValidationBusiness() {
    return documentValidationBusiness;
  }

  public void setDocumentValidationBusiness(DocumentValidationBusiness documentValidationBusiness) {
    this.documentValidationBusiness = documentValidationBusiness;
  }

  public DocumentParser getDocumentParser() {
    return documentParser;
  }

  public void setDocumentParser(DocumentParser documentParser) {
    this.documentParser = documentParser;
  }

  public DocumentTransformation getDocumentTransformation() {
    return documentTransformation;
  }

  public void setDocumentTransformation(DocumentTransformation documentTransformation) {
    this.documentTransformation = documentTransformation;
  }

  public OutgoingShipment getOutgoingShipment() {
    return outgoingShipment;
  }

  public void setOutgoingShipment(OutgoingShipment outgoingShipment) {
    this.outgoingShipment = outgoingShipment;
  }

  public SenderEndpoint getSenderEndpoint() {
    return senderEndpoint;
  }

  public void setSenderEndpoint(SenderEndpoint senderEndpoint) {
    this.senderEndpoint = senderEndpoint;
  }

}
