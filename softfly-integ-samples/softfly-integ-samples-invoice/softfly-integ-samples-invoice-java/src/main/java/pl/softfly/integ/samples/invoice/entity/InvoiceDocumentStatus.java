package pl.softfly.integ.samples.invoice.entity;

import pl.softfly.integ.doc.entity.DocumentStatus;

/**
 * Entity.
 *
 * @author Grzegorz Ziemski
 */
public enum InvoiceDocumentStatus implements DocumentStatus {
  LOADED, //
  NOT_RECOGNIZED, //
  RECOGNIZED, //
  SCHEME_VALID, //
  SCHEME_NOT_VALID, //
  BUSINESS_VALID, //
  BUSINESS_NOT_VALID, //
  PARSED, //
  SUBMITTED
}
