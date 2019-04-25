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


import java.util.List;

public class JavaMainFlowBean implements MainFlow {

	protected DocumentRepository documentRepository;

	protected DocumentRecognize documentRecognize;

	protected DocumentValidationSchema documentValidationSchema;

	protected DocumentValidationBusiness documentValidationBusiness;

	protected DocumentParser documentParser;

	protected DocumentTransformation documentTransformation;

	protected DetermineEndpointsBean determineEndpoints;

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
			for (Endpoint endpoint: recipient.getEndpoints()) {

				for (DocumentBody documentBody: documentHeader.getBodies()) {
					DocumentBody newBody = transformDocument(documentBody, endpoint.getDictDocumentFormat());
					// 9. Send
					if (sendDocument(newBody, endpoint)) {
						break RECIPIENT;
					}
				}
			}
		}

		response.setResult(documentHeader.getStatus().toString());

		return response;
	}

	protected DocumentHeader createDocumentHeader(String inputDocument) {
		DocumentHeader header = documentRepository.createDocumentHeader(inputDocument);
		header.setStatus(InvoiceDocumentStatus.LOADED);
		return header;
	}

	protected boolean recognizeDocument(DocumentHeader header) {
		boolean isRecognize = DocumentRecognizeLocalBean.enrichRecognize(documentRecognize, header);
		header.setStatus(isRecognize ? InvoiceDocumentStatus.RECOGNIZED : InvoiceDocumentStatus.NOT_RECOGNIZED);
		return isRecognize;
	}

	protected List<?> validateDocumentSchema(DocumentHeader header) {
		List<?> list = documentValidationSchema.validate(header);
		boolean isValid = list == null || list.isEmpty();
		header.setStatus(isValid ? InvoiceDocumentStatus.SCHEME_VALIDATED : InvoiceDocumentStatus.NOT_SCHEME_VALIDATED);
		return list;
	}

	protected List<?> validateDocumentBusiness(DocumentHeader documentHeader) {
		List<?> list = documentValidationBusiness.validate(documentHeader);
		boolean isValid = list == null || list.isEmpty();
		documentHeader.setStatus(
				isValid ? InvoiceDocumentStatus.BUSINESS_VALIDATED : InvoiceDocumentStatus.NOT_BUSINESS_VALIDATED);
		return list;
	}

	protected void parseDocument(DocumentHeader documentHeader) {
		documentParser.parse(documentHeader);
		documentHeader.setStatus(InvoiceDocumentStatus.PARSED);
	}

	protected DocumentBody transformDocument(DocumentBody documentBody, DictDocumentFormat targetDocumentFormat) {
		if (documentBody.getDocumentFormat().equals(targetDocumentFormat)) {
			return documentBody;
		} else {
			DocumentBody newBody = documentTransformation.transform(documentBody, targetDocumentFormat);
			documentBody.getDocumentHeader().getBodies().add(newBody);
			return newBody;
		}
	}

	protected boolean sendDocument(DocumentBody documentBody, Endpoint endpoint2) {
		if (endpoint.send(documentBody, endpoint2)) {
			documentBody.getDocumentHeader().setStatus(InvoiceDocumentStatus.SUBMITTED);
			return true;
		} else {
			return false;
		}
	}

}
