package pl.softfly.integ.samples.invoice.jaxws.doc.transformation;

import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;

public class DocumentTransformationBean
    implements pl.softfly.integ.doc.transformation.DocumentTransformation {

  private DocumentTransformation documentTransformation = new DocumentTransformation();

  private DocumentTransformationService documentTransformationService =
      documentTransformation.getDocumentTransformationServicePort();

  @Override
  public boolean isSupported(DocumentFormat sourceDocumentFormat,
      DocumentFormat targetDocumentFormat) {
    return documentTransformationService.isSupported(sourceDocumentFormat, targetDocumentFormat);
  }

  @Override
  public DocumentBody transform(DocumentBody sourceDocumentBody,
      DocumentFormat targetDocumentFormat) {
    return documentTransformationService.transform(sourceDocumentBody, targetDocumentFormat);
  }

}
