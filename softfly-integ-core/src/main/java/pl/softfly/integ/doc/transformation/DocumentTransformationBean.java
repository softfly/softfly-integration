package pl.softfly.integ.doc.transformation;

import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;


/**
 * Transform the document to other format.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentTransformationBean implements DocumentTransformation {

  private static final Logger LOGGER = Logger.getLogger(DocumentTransformationBean.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSupported(DocumentFormat sourceDocumentFormat,
      DocumentFormat targetDocumentFormat) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DocumentBody transform(DocumentBody sourceDocumentBody,
      DocumentFormat targetDocumentFormat) {
    Objects.requireNonNull(sourceDocumentBody);
    Objects.requireNonNull(targetDocumentFormat);
    LOGGER.info("Transform from " + sourceDocumentBody.getDocumentFormat().getName() + " to "
        + targetDocumentFormat.getName());

    DocumentBody targetDocumentBody = new DocumentBody();
    targetDocumentBody.setBody(sourceDocumentBody.getBody());
    targetDocumentBody.setDocumentHeader(sourceDocumentBody.getDocumentHeader());
    targetDocumentBody.setDocumentFormat(targetDocumentFormat);
    targetDocumentBody.setVersion(sourceDocumentBody.getVersion());
    return targetDocumentBody;
  }

}
