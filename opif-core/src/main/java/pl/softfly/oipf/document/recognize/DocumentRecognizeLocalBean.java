package pl.softfly.oipf.document.recognize;

import java.util.Objects;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public class DocumentRecognizeLocalBean {
	
	protected DocumentRecognizeBean documentRecognize;
	
	protected static DocumentRecognizeLocalBean INSTANCE;
	
	public DocumentRecognizeLocalBean(DocumentRecognizeBean documentRecognize) {
		this.documentRecognize = documentRecognize;
		INSTANCE = this;
	}
	
	public static boolean enrichRecognize(DocumentRecognize documentRecognize, DocumentHeader documentHeader) {
		Objects.requireNonNull(documentHeader);

		boolean isRecognize = false;
		for (DocumentBody body : documentHeader.getBodies()) {
			body.setDocumentFormat(documentRecognize.recognize(body));
			
			if (body.getDocumentFormat() != null) {
				if (body.getDocumentFormat().getDocumentBusinessType() != null) {
					documentHeader.getDocumentBusinessType().add(body.getDocumentFormat().getDocumentBusinessType());
				}
				isRecognize=true;
			}
		}

		return isRecognize;
	}
	
	public boolean enrichRecognize(DocumentHeader documentHeader) {
		return enrichRecognize(documentRecognize, documentHeader);
	}
	
	public synchronized DocumentRecognizeLocalBean instance() {
		Objects.nonNull(INSTANCE);
		return INSTANCE;
	}

}
