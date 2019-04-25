package pl.softfly.samples.invoice.entity;

import pl.softfly.oipf.entity.DocumentStatus;

public enum InvoiceDocumentStatus implements DocumentStatus {
	LOADED, //
	NOT_RECOGNIZED, //
	RECOGNIZED, //
	NOT_SCHEME_VALIDATED, //
	SCHEME_VALIDATED, //
	NOT_BUSINESS_VALIDATED, //
	BUSINESS_VALIDATED, //
	PARSED, //
	SUBMITTED
}
