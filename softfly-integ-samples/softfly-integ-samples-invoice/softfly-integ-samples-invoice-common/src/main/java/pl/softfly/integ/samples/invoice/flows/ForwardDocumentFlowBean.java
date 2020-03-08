package pl.softfly.integ.samples.invoice.flows;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.ProcessingLog;
import pl.softfly.integ.doc.parser.DocumentParser;
import pl.softfly.integ.doc.recognize.DocumentRecognizeFormat;
import pl.softfly.integ.doc.repository.DocumentHeaderRepositoryBean;
import pl.softfly.integ.doc.transformation.DocumentTransformation;
import pl.softfly.integ.doc.utils.DocumentMapperBean;
import pl.softfly.integ.doc.validation.business.DocumentValidationBusiness;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchema;
import pl.softfly.integ.endpoint.SenderEndpoint;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.samples.invoice.entity.InvoiceDocumentStatus;
import pl.softfly.integ.shipment.entity.ShipmentIncoming;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;
import pl.softfly.integ.shipment.out.OutgoingShipment;


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
 * <td>(Optional) Transform to the supported {@link DocumentFormat} by {@link DocumentParser}.</td>
 * <td>{@link DocumentParser}</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>Parse the document to POJO (recipients).</td>
 * <td>{@link DocumentParser}</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>Other business steps. (Changes in the content of the document.)</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>Determine the shipment (endpoints, recipient) to which the document should be sent.</td>
 * <td>{@link OutgoingShipment}</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>Generate the document from POJO.</td>
 * <td>{@link DocumentTransformation}</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>(Optional) Transform to the recipient's document format.</td>
 * <td>{@link DocumentTransformation}</td>
 * </tr>
 * <tr>
 * <td>10</td>
 * <td>Send the document.</td>
 * <td>{@link SenderEndpoint}</td>
 * </tr>
 * </table>
 */
public class ForwardDocumentFlowBean {

  protected static final int LIMIT_TRY_DETERMINE_SHIPMENT = 10;
  protected DocumentHeaderRepositoryBean documentHeaderRepository =
      new DocumentHeaderRepositoryBean();
  protected DocumentMapperBean documentMapper = new DocumentMapperBean();
  private DocumentRecognizeFormat documentRecognize;
  private DocumentValidationSchema documentValidationSchema;
  private DocumentValidationBusiness documentValidationBusiness;
  private DocumentParser documentParser;
  private DocumentTransformation documentTransformation;
  private OutgoingShipment outgoingShipment;
  private SenderEndpoint senderEndpoint;

  public ForwardDocumentFlowResponse start(ShipmentIncoming shipmentIncoming) {
    ForwardDocumentFlowResponse response = new ForwardDocumentFlowResponse();

    // Receive the document from the endpoint.
    DocumentHeader documentHeader = shipmentIncoming.getDocumentHeader();
    shipmentIncoming.setDocumentHeader(null);
    documentHeader.setShipments((new LinkedList<>(Arrays.asList(shipmentIncoming))));

    // 1. Recognize the document format.
    if (!recognizeDocument(documentHeader)) {
      response.setResult(documentHeader.getStatus().toString());
      return response;
    }

    // 2. Validate schema of the document.
    if (!validateDocumentSchema(documentHeader)) {
      response.setResult(documentHeader.getStatus().toString());
      response
          .setErrors(documentHeader.getBodies().stream().flatMap(b -> b.getValidations().stream())
              .collect(Collectors.toList()));
      return response;
    }

    // 3. (Optional) Transform to the supported document format by {@link
    // DocumentValidationBusiness}.
    ProcessingLog error = transformBeforeValidateDocumentBusiness(documentHeader);
    if (error != null) {
      response.setResult(documentHeader.getStatus().toString());
      response.setErrors(Arrays.asList(error.getMsg()));
      return response;
    }

    // 4. Validate business validation of the document.
    if (!validateDocumentBusiness(documentHeader)) {
      response.setResult(documentHeader.getStatus().toString());
      response
          .setErrors(documentHeader.getBodies().stream().flatMap(b -> b.getValidations().stream())
              .collect(Collectors.toList()));
      return response;
    }

    // 5. (Optional) Transform to the supported {@link DocumentFormat} by {@link DocumentParser}.
    error = transformBeforeParseDocument(documentHeader);
    if (error != null) {
      response.setResult(documentHeader.getStatus().toString());
      response.setErrors(Arrays.asList(error.getMsg()));
      return response;
    }

    // 6. Parse the document to POJO (recipients).
    documentHeader = parseDocument(documentHeader);

    // Other business steps.

    for (int i = 0; ; i++) {
      if (i > LIMIT_TRY_DETERMINE_SHIPMENT) {
        throw new RuntimeException("LIMIT_TRY_DETERMINE_SHIPMENT");
      } else {
        // 7. Determine the outgoing shipment (endpoints, recipient) to which the document should be
        // sent.
        DocumentHeader documentHeaderIn = createOutgoingShipmentRequest(documentHeader);
        Collection<ShipmentOutgoing> shipmentsOut = outgoingShipment.determine(documentHeaderIn);
        if (CollectionUtils.isEmpty(shipmentsOut)) {
          mergeOutgoingShipmentResponse(documentHeader, shipmentsOut);

          for (ShipmentOutgoing shipment : getDocumentHeaderRepository()
              .findOutgoingShipmentStream(documentHeader)
              .filter(s -> ShipmentOutgoingStatus.AWAITING_SEND.equals(s.getStatus()))
              .collect(Collectors.toList())) {
            Endpoint endpoint = shipment.getEndpoint();

            // 8. Generate the document from POJO.
            // 9. (Optional) Transform to the recipient's document format.
            if (!shipment.getDocumentBody().getDocumentFormat()
                .equals(endpoint.getDocumentFormat())) {
              DocumentBody documentBody = shipment.getDocumentBody();
              documentBody =
                  transformDocument(documentHeader, documentBody, endpoint.getDocumentFormat());
              shipment.setDocumentBody(documentBody);
            }

            // 10. Send document.
            sendDocument(documentHeader, shipment);
          }
        } else {
          documentHeader.setStatus(InvoiceDocumentStatus.SUBMITTED);
          break;
        }
      }
    }

    response.setResult(documentHeader.getStatus().toString());
    return response;
  }

  /**
   * 1. Recognize the document format.
   */
  protected boolean recognizeDocument(final DocumentHeader documentHeader) {
    DocumentHeader documentHeaderOut =
        getDocumentRecognize().recognize(createRecognizeDocumentRequest(documentHeader));
    mergeRecognizeDocumentResponse(documentHeader, documentHeaderOut);

    boolean isRecognize = documentHeader.getDocumentBusinessType() != null;
    documentHeader.setStatus(
        isRecognize ? InvoiceDocumentStatus.RECOGNIZED : InvoiceDocumentStatus.NOT_RECOGNIZED);
    return isRecognize;
  }

  /**
   * 1. Recognize the document format.
   */
  protected DocumentHeader createRecognizeDocumentRequest(final DocumentHeader documentHeader) {
    DocumentHeader documentHeaderIn = new DocumentHeader();
    documentHeaderIn.setBodies(documentHeader.getBodies().stream()
        .map(getDocumentMapper()::cloneDocumentBody).collect(Collectors.toList()));
    return documentHeaderIn;
  }

  /**
   * 1. Recognize the document format.
   */
  protected void mergeRecognizeDocumentResponse(final DocumentHeader documentHeader,
      final DocumentHeader documentHeaderOut) {
    documentHeader.setDocumentBusinessType(documentHeaderOut.getDocumentBusinessType());
    for (DocumentBody documentBodyOut : documentHeaderOut.getBodies()) {
      DocumentBody documentBody =
          getDocumentHeaderRepository()
              .findDocumentBodyById(documentHeader, documentBodyOut.getId());
      documentBody.setDocumentFormat(documentBodyOut.getDocumentFormat());
      documentBody.getProcessingLogs().addAll(documentBodyOut.getProcessingLogs());
    }
  }

  /**
   * 2. Validate schema of the document.
   */
  protected boolean validateDocumentSchema(final DocumentHeader documentHeader) {
    DocumentHeader documentHeaderOut =
        getDocumentValidationSchema().validate(createValidateDocumentSchemaRequest(documentHeader));
    mergeValidateDocumentSchemaResponse(documentHeader, documentHeaderOut);

    boolean isValid = true;
    for (DocumentBody documentBody : documentHeader.getBodies()) {
      if (CollectionUtils.isNotEmpty(documentBody.getValidations())) {
        isValid = false;
      }
    }

    documentHeader.setStatus(
        isValid ? InvoiceDocumentStatus.SCHEME_VALID : InvoiceDocumentStatus.SCHEME_NOT_VALID);
    return isValid;
  }

  /**
   * 2. Validate schema of the document.
   */
  protected DocumentHeader createValidateDocumentSchemaRequest(
      final DocumentHeader documentHeader) {
    DocumentHeader documentHeaderIn = new DocumentHeader();
    documentHeaderIn.setDocumentBusinessType(
        getDocumentMapper().cloneDocumentBusinessTypes(documentHeader.getDocumentBusinessType()));
    documentHeaderIn.setBodies(documentHeader.getBodies().stream()
        .map(getDocumentMapper()::cloneDocumentBody).collect(Collectors.toList()));
    return documentHeaderIn;
  }

  /**
   * 2. Validate schema of the document.
   */
  protected void mergeValidateDocumentSchemaResponse(final DocumentHeader documentHeader,
      final DocumentHeader documentHeaderOut) {
    for (DocumentBody documentBodyOut : documentHeaderOut.getBodies()) {
      DocumentBody documentBody =
          getDocumentHeaderRepository()
              .findDocumentBodyById(documentHeader, documentBodyOut.getId());
      documentBody.getProcessingLogs().addAll(documentBodyOut.getProcessingLogs());
      documentBody.getValidations().addAll(documentBodyOut.getValidations());
    }
  }

  /**
   * 3. (Optional) Transform to the supported {@link DocumentFormat} by {@link
   * DocumentValidationBusiness}.
   *
   * <p>
   * 3.1. If {@link DocumentBody} exists in a supported {@link DocumentFormat}, USE CASE END.
   * <p>
   * 3.2. Transform to a supported {@link DocumentFormat} by {@link DocumentValidationBusiness}, USE
   * CASE END.
   * <p>
   * 3.2.E If transform to the supported {@link DocumentFormat} is not possible, add {@link
   * ProcessingLog} with the error.
   *
   * @return ProcessingLog with a error
   */
  protected ProcessingLog transformBeforeValidateDocumentBusiness(DocumentHeader documentHeader) {
    List<DocumentFormat> supportedDocumentFormatsOut =
        getDocumentValidationBusiness()
            .getSupported(new LinkedList<DocumentBusinessType>(documentHeader.getDocumentBusinessType()));

    // 3.1.
    if (documentHeader.getBodies().stream()
        .filter(
            documentBody -> supportedDocumentFormatsOut.contains(documentBody.getDocumentFormat()))
        .findAny().isPresent()) {
      return null;
    }

    // 3.2.
    for (DocumentBody body : documentHeader.getBodies()) {
      for (DocumentFormat supportedDocumentFormat : supportedDocumentFormatsOut) {
        if (getDocumentTransformation().isSupported(body.getDocumentFormat(),
            supportedDocumentFormat)) {
          DocumentBody supportedDocumentBody =
              transformDocument(documentHeader, body, supportedDocumentFormat);
          if (supportedDocumentBody != null) {
            ProcessingLog log = new ProcessingLog();
            log.setSource(ForwardDocumentFlowBean.class.getSimpleName());
            log.setMsg(
                "Required transformation for a supported DocumentFormat for DocumentValidationBusiness.");
            supportedDocumentBody.getProcessingLogs().add(log);
            return null;
          }
        }
      }
    }

    // 3.2.E.
    documentHeader.setStatus(InvoiceDocumentStatus.BUSINESS_NOT_VALID);
    ProcessingLog log = new ProcessingLog();
    log.setSource(ForwardDocumentFlowBean.class.getSimpleName());
    log.setMsg(
        "ERROR: It is not possible to transform into a document format supported by DocumentValidationBusiness.");
    documentHeader.getProcessingLogs().add(log);
    return log;
  }

  /**
   * 4. Validate business validation of the document.
   */
  protected boolean validateDocumentBusiness(DocumentHeader documentHeader) {
    DocumentHeader documentHeaderOut = getDocumentValidationBusiness()
        .validate(createValidateDocumentSchemaRequest(documentHeader));
    mergeValidateDocumentSchemaResponse(documentHeader, documentHeaderOut);

    boolean isValid = true;
    for (DocumentBody documentBody : documentHeader.getBodies()) {
      if (CollectionUtils.isNotEmpty(documentBody.getValidations())) {
        isValid = false;
      }
    }

    documentHeader.setStatus(
        isValid ? InvoiceDocumentStatus.BUSINESS_VALID : InvoiceDocumentStatus.BUSINESS_NOT_VALID);
    return isValid;
  }

  /**
   * 5. (Optional) Transform to the supported {@link DocumentFormat} by {@link DocumentParser}.
   *
   * <p>
   * 5.1. If {@link DocumentBody} exists in a supported {@link DocumentFormat}, USE CASE END.
   * <p>
   * 3.2. Transform to a supported {@link DocumentFormat} by {@link DocumentParser}, USE CASE END.
   * <p>
   * 3.2.E If transform to the supported {@link DocumentFormat} is not possible, add {@link
   * ProcessingLog} with the error.
   *
   * @return ProcessingLog with a error
   */
  protected ProcessingLog transformBeforeParseDocument(DocumentHeader documentHeader) {
    List<DocumentFormat> documentFormatsOut =
        getDocumentParser().getSupported(documentHeader.getDocumentBusinessType());

    // 5.1.
    if (documentHeader.getBodies().stream()
        .filter(documentBody -> documentFormatsOut.contains(documentBody.getDocumentFormat()))
        .findAny().isPresent()) {
      return null;
    }

    // 5.2.
    for (DocumentBody body : documentHeader.getBodies()) {
      for (DocumentFormat supportedDocumentFormat : documentFormatsOut) {
        if (getDocumentTransformation().isSupported(body.getDocumentFormat(),
            supportedDocumentFormat)) {
          DocumentBody supportedDocumentBody =
              transformDocument(documentHeader, body, supportedDocumentFormat);
          if (supportedDocumentBody != null) {
            ProcessingLog log = new ProcessingLog();
            log.setSource(ForwardDocumentFlowBean.class.getSimpleName());
            log.setMsg(
                "Required transformation for a supported DocumentFormat for DocumentParser.");
            supportedDocumentBody.getProcessingLogs().add(log);
            return null;
          }
        }
      }
    }

    // 5.2.E.
    documentHeader.setStatus(InvoiceDocumentStatus.NOT_PARSED);
    ProcessingLog log = new ProcessingLog();
    log.setSource(ForwardDocumentFlowBean.class.getSimpleName());
    log.setMsg(
        "ERROR: It is not possible to transform into a document format supported by DocumentParser.");
    documentHeader.getProcessingLogs().add(log);
    return log;
  }

  /**
   * 6. Parse the document to POJO (recipients).
   */
  protected DocumentHeader parseDocument(final DocumentHeader documentHeader) {
    DocumentHeader documentHeaderOut = getDocumentParser()
        .parse(createParseDocumentRequest(documentHeader));
    mergeParseDocumentResponse(documentHeader, documentHeaderOut);
    return documentHeader;
  }

  /**
   * 6. Parse the document to POJO (recipients).
   */
  protected DocumentHeader createParseDocumentRequest(final DocumentHeader documentHeader) {
    DocumentHeader cocumentHeaderIn = new DocumentHeader();
    cocumentHeaderIn.setDocumentBusinessType(
        getDocumentMapper().cloneDocumentBusinessTypes(documentHeader.getDocumentBusinessType()));
    cocumentHeaderIn.setBodies(documentHeader.getBodies().stream()
        .map(getDocumentMapper()::cloneDocumentBody).collect(Collectors.toList()));
    return cocumentHeaderIn;
  }

  /**
   * 6. Parse the document to POJO (recipients).
   */
  protected void mergeParseDocumentResponse(final DocumentHeader documentHeader,
      final DocumentHeader documentHeaderOut) {
    documentHeader.getRecipients().addAll(documentHeaderOut.getRecipients());
    List<DocumentBody> DocumentBodiesOut = documentHeaderOut.getBodies();
    for (DocumentBody documentBodyOut : DocumentBodiesOut) {
      documentBodyOut.setId(getDocumentHeaderRepository().findNextDocumentBodyId(documentHeader));
    }
    documentHeader.getBodies().addAll(DocumentBodiesOut);
  }


  /**
   * 7. Determine the outgoing shipment (endpoints, recipient) to which the document should be
   * sent.
   */
  protected DocumentHeader createOutgoingShipmentRequest(final DocumentHeader source) {
    DocumentHeader target = new DocumentHeader();

    target.setRecipients(source.getRecipients().stream().collect(Collectors.toList()));

    target.setBodies(source.getBodies().stream().map(getDocumentMapper()::cloneShortDocumentBody)
        .collect(Collectors.toList()));

    target.setShipments(source.getShipments().stream()//
        .filter(ShipmentOutgoing.class::isInstance)//
        .map(ShipmentOutgoing.class::cast)//
        .map(getDocumentMapper()::cloneShipmentOutgoing)//
        .collect(Collectors.toList()));

    return target;
  }

  /**
   * 7. Determine the outgoing shipment (endpoints, recipient) to which the document should be
   * sent.
   */
  protected void mergeOutgoingShipmentResponse(final DocumentHeader documentHeader,
      Collection<ShipmentOutgoing> shipmentsOut) {
    documentHeader.getShipments().addAll(shipmentsOut);
  }

  /**
   * <p>
   * 3. (Optional) Transform to the supported {@link DocumentFormat} by {@link
   * DocumentValidationBusiness}.
   * <p>
   * 5. (Optional) Transform to the supported {@link DocumentFormat} by {@link DocumentParser}.
   * <p>
   * 9. (Optional) Transform to the recipient's document format.
   */
  protected DocumentBody transformDocument(final DocumentHeader documentHeader,
      final DocumentBody documentBody, final DocumentFormat targetDocumentFormat) {
    DocumentBody targetDocumentBody = getDocumentTransformation()
        .transform(createTransformDocumentRequest(documentBody), targetDocumentFormat);
    mergeTransformDocumentResponse(documentHeader, targetDocumentBody);
    return targetDocumentBody;
  }

  protected DocumentBody createTransformDocumentRequest(final DocumentBody documentBody) {
    return getDocumentMapper().cloneDocumentBody(documentBody);
  }

  protected void mergeTransformDocumentResponse(final DocumentHeader documentHeader,
      DocumentBody documentBodyOut) {
    documentBodyOut.setId(getDocumentHeaderRepository().findNextDocumentBodyId(documentHeader));
    documentHeader.getBodies().add(documentBodyOut);
  }

  /**
   * 10. Send document.
   */
  protected void sendDocument(final DocumentHeader documentHeader,
      final ShipmentOutgoing shipment) {
    ShipmentOutgoing shipmentOut =
        getSenderEndpoint().send(createSendDocumentRequest(documentHeader, shipment));
    mergeOutgoingShipmentResponse(documentHeader, shipmentOut);
  }

  /**
   * 10. Send document.
   */
  protected ShipmentOutgoing createSendDocumentRequest(final DocumentHeader documentHeader,
      final ShipmentOutgoing shipment) {
    ShipmentOutgoing shipmentIn = new ShipmentOutgoing();
    shipmentIn.setId(shipment.getId());
    shipmentIn.setDocumentBody(getDocumentMapper().cloneDocumentBody(
        getDocumentHeaderRepository().findDocumentBodyById(documentHeader, shipment.getId())));
    shipmentIn.setEndpoint(getDocumentMapper().cloneEndpoint(shipment.getEndpoint()));
    return shipmentIn;
  }

  protected void mergeOutgoingShipmentResponse(final DocumentHeader documentHeader,
      final ShipmentOutgoing shipmentOut) {
    ShipmentOutgoing shipmentOutgoing =
        getDocumentHeaderRepository().findOutgoingShipmentById(documentHeader, shipmentOut.getId());
    shipmentOutgoing.setStatus(shipmentOut.getStatus());
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

  public DocumentHeaderRepositoryBean getDocumentHeaderRepository() {
    return documentHeaderRepository;
  }

  public void setDocumentHeaderRepository(DocumentHeaderRepositoryBean documentHeaderRepository) {
    this.documentHeaderRepository = documentHeaderRepository;
  }

  public DocumentMapperBean getDocumentMapper() {
    return documentMapper;
  }

  public void setDocumentMapper(DocumentMapperBean documentMapper) {
    this.documentMapper = documentMapper;
  }
}
