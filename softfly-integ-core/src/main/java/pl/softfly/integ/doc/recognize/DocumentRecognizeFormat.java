package pl.softfly.integ.doc.recognize;

import pl.softfly.integ.doc.entity.DocumentHeader;

/**
 * Recognize the document format e.g. XML, EDIFACT, Invoice.
 */
public interface DocumentRecognizeFormat {

  /**
   * Recognize the document format e.g. XML, EDIFACT, Invoice.
   */
  DocumentHeader recognize(DocumentHeader documentHeader);

}
