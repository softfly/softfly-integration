package pl.softfly.integ.samples.invoice.jaxws.doc.recognize;

import pl.softfly.integ.doc.entity.DocumentHeader;

public class DocumentRecognizeFormatBean
    implements pl.softfly.integ.doc.recognize.DocumentRecognizeFormat {

  private DocumentRecognizeFormat documentRecognizeFormat = new DocumentRecognizeFormat();

  private DocumentRecognizeFormatService documentRecognizeFormatService =
      documentRecognizeFormat.getDocumentRecognizeFormatServicePort();

  @Override
  public DocumentHeader recognize(DocumentHeader documentHeader) {
    return documentRecognizeFormatService.recognize(documentHeader);
  }

}
