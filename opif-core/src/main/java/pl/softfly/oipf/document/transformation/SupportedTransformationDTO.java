package pl.softfly.oipf.document.transformation;

import pl.softfly.oipf.entity.DictDocumentFormat;

class SupportedTransformationDTO {
	
	DictDocumentFormat from;
	
	DictDocumentFormat to;

	public DictDocumentFormat getFrom() {
		return from;
	}

	public void setFrom(DictDocumentFormat from) {
		this.from = from;
	}

	public DictDocumentFormat getTo() {
		return to;
	}

	public void setTo(DictDocumentFormat to) {
		this.to = to;
	}
	
}
