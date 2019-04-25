package pl.softfly.oipf.samples.invoice.flows.java.ejb;

import pl.softfly.oipf.document.parser.DocumentParser;
import pl.softfly.oipf.document.recognize.DocumentRecognize;
import pl.softfly.oipf.document.recognize.DocumentRecognizeLocalBean;
import pl.softfly.oipf.document.transformation.DocumentTransformation;
import pl.softfly.oipf.document.validation.business.DocumentValidationBusiness;
import pl.softfly.oipf.document.validation.schema.DocumentValidationSchema;
import pl.softfly.oipf.endpoint.DetermineEndpointsBean;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItemStatus;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.opif.document.repo.DocumentRepository;
import pl.softfly.samples.invoice.entity.InvoiceDocumentStatus;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import java.util.List;

/**
 * Session Bean implementation class MainFlow
 */
@Stateless
@LocalBean
public class JavaMainFlowBean implements MainFlow {

	@EJB
	protected DocumentRepository documentRepository;

	@EJB
	protected DocumentRecognize documentRecognize;

	@EJB
	protected DocumentValidationSchema documentValidationSchema;

	@EJB
	protected DocumentValidationBusiness documentValidationBusiness;

	@EJB
	protected DocumentParser documentParser;

	@EJB
	protected DocumentTransformation documentTransformation;

	@EJB
	protected DetermineEndpointsBean determineEndpoints;

	@EJB
	protected pl.softfly.oipf.endpoint.Endpoint endpoint;

	@Override
	public JavaMainFlowResponse start(String inputDocument) {
		JavaMainFlowResponse response = new JavaMainFlowResponse();

		DocumentHeader documentHeader = createDocumentHeader(inputDocument);

		// 1. Rozpoznaj format dokumentu.
		if (!recognizeDocument(documentHeader)) {
			response.setResult(documentHeader.getStatus().toString());
			return response;
		}

		// 2. Walidacja synaktyczną dokumentu.
		List<?> errors = validateDocumentSchema(documentHeader);
		if (errors != null && !errors.isEmpty()) {
			response.setResult(documentHeader.getStatus().toString());
			response.setErrors(errors);
			return response;
		}

		// 3. Walidacja biznesowa dokumentu.
		errors = validateDocumentBusiness(documentHeader);
		if (errors != null && !errors.isEmpty()) {
			response.setResult(documentHeader.getStatus().toString());
			response.setErrors(errors);
			return response;
		}

		// Uzupełnij encje o informacje o dokumencie.
		parseDocument(documentHeader);

		// 4. Dermine recipients of document.
		RECIPIENT: for (Participant recipient : documentHeader.getRecipients()) {

			// 7. Determine endpoints to sent.
			List<DocumentShipmentItem> shipmentList = determineEndpoints.determineShipment(documentHeader, recipient);
			for (DocumentShipmentItem shipmentItem: shipmentList) {
				DocumentBody documentBody = shipmentItem.getDocumentBody();
				Endpoint endpoint = shipmentItem.getEndpoint();

				// 8. Transform
				DocumentBody newBody = transformDocument(documentBody, endpoint.getDictDocumentFormat());
				if (newBody != null) {
					shipmentItem.setDocumentBody(documentBody);
				}

				// 9. Send
				if (sendDocument(shipmentItem)) {
					break RECIPIENT;
				}
			}
		}

		response.setResult(documentHeader.getStatus().toString());

		return response;
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.CreateDocumentHeaderWorkItemHandler
	 */
	protected DocumentHeader createDocumentHeader(String inputDocument) {
		DocumentHeader header = documentRepository.createDocumentHeader(inputDocument);
		header.setStatus(InvoiceDocumentStatus.LOADED);
		return header;
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.RecognizeDocumentWorkItemHandler
	 */
	protected boolean recognizeDocument(DocumentHeader header) {
		boolean isRecognize = DocumentRecognizeLocalBean.enrichRecognize(documentRecognize, header);
		header.setStatus(isRecognize ? InvoiceDocumentStatus.RECOGNIZED : InvoiceDocumentStatus.NOT_RECOGNIZED);
		return isRecognize;
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.ValidateSchemaDocumentWorkItemHandler
	 */
	protected List<?> validateDocumentSchema(DocumentHeader header) {
		List<?> list = documentValidationSchema.validate(header);
		boolean isValid = list == null || list.isEmpty();
		header.setStatus(isValid ? InvoiceDocumentStatus.SCHEME_VALIDATED : InvoiceDocumentStatus.NOT_SCHEME_VALIDATED);
		return list;
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.ValidateBusinessDocumentWorkItemHandler
	 */
	protected List<?> validateDocumentBusiness(DocumentHeader documentHeader) {
		List<?> list = documentValidationBusiness.validate(documentHeader);
		boolean isValid = list == null || list.isEmpty();
		documentHeader.setStatus(
				isValid ? InvoiceDocumentStatus.BUSINESS_VALIDATED : InvoiceDocumentStatus.NOT_BUSINESS_VALIDATED);
		return list;
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.ParseDocumentWorkItemHandler
	 */
	protected void parseDocument(DocumentHeader documentHeader) {
		documentParser.parse(documentHeader);
		documentHeader.setStatus(InvoiceDocumentStatus.PARSED);
	}

	/**
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.FindSupportedDocumentFormats
	 */
	/*
	protected List<DictDocumentFormat> findSupportedDocumentFormat(DocumentHeader documentHeader,
			Participant recipient) {
		return endpoint.findSupportedDocumentFormat(documentHeader, recipient);
	}*/

	/**
	 * TODO fix bpmn
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.TransformDocumentWorkItemHandler
	 */
	protected DocumentBody transformDocument(DocumentBody documentBody, DictDocumentFormat targetDocumentFormat) {
		if (!documentBody.getDocumentFormat().equals(targetDocumentFormat)) {
			DocumentBody newBody = documentTransformation.transform(documentBody, targetDocumentFormat);
			documentBody.getDocumentHeader().getBodies().add(newBody);
			return documentBody;
		}
		return null;
	}

	/**
	 * TODO fix bpmn
	 * @see pl.softfly.opif.samples.invoice.flows.jbpm.workitemhandler.SendDocumentWorkItemHandler
	 */
	protected boolean sendDocument(DocumentShipmentItem shipmentItem) {
		DocumentBody documentBody = shipmentItem.getDocumentBody();
		Endpoint endpoint2 = shipmentItem.getEndpoint();

		if (endpoint.send(documentBody, endpoint2)) {
			documentBody.getDocumentHeader().setStatus(InvoiceDocumentStatus.SUBMITTED);
			shipmentItem.setStatus(DocumentShipmentItemStatus.SENT);
			return true;
		} else {
			shipmentItem.setStatus(DocumentShipmentItemStatus.ERROR);
			return false;
		}
	}

}
