package pl.softfly.oipf.samples.invoice.flows.java.ejb;

import java.util.List;

import pl.softfly.oipf.entity.DocumentHeader;

public class JavaMainFlowResponse {
	
	private String result;
	
	private DocumentHeader documentHeader;
	
	private List<?> errors;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public DocumentHeader getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	public List<?> getErrors() {
		return errors;
	}

	public void setErrors(List<?> errors) {
		this.errors = errors;
	}

}
