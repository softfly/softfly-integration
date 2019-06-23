package pl.softfly.integ.doc.recognize;

import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Singleton for {@link DocumentRecognizeFormatUtil}.
 *
 * <p>
 * This logic is not in service for reduce the amount of unnecessary data being transported. Often
 * repeated logic.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentRecognizeFormatWrapper {

  protected DocumentRecognizeFormat documentRecognizeFormat;

  public DocumentRecognizeFormatWrapper(DocumentRecognizeFormat documentRecognize) {
    this.documentRecognizeFormat = documentRecognize;
  }

  /**
   * Enrich the document with the returned object from {@link DocumentRecognizeFormat#recognize}.
   */
  public boolean enrichRecognize(DocumentHeader documentHeader) {
    return DocumentRecognizeFormatUtil.enrichRecognize(getDocumentRecognizeFormat(),
        documentHeader);
  }

  public DocumentRecognizeFormat getDocumentRecognizeFormat() {
    return documentRecognizeFormat;
  }

  public void setDocumentRecognizeFormat(DocumentRecognizeFormat documentRecognizeFormat) {
    this.documentRecognizeFormat = documentRecognizeFormat;
  }

}
