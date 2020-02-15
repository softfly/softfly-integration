package pl.softfly.integ.doc.transformation;

import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.ProcessingLog;


public class DocumentTransformationBean implements DocumentTransformation {

  private static final Logger LOGGER = Logger.getLogger(DocumentTransformation.class.getName());

  @Override
  public boolean isSupported(DocumentFormat sourceDocumentFormat,
      DocumentFormat targetDocumentFormat) {
    return true;
  }

  @Override
  public DocumentBody transform(DocumentBody sourceDocumentBody,
      DocumentFormat targetDocumentFormat) {
    Objects.requireNonNull(sourceDocumentBody);
    Objects.requireNonNull(targetDocumentFormat);

    String msg = "Transform from " + sourceDocumentBody.getDocumentFormat().getName() + " to "
        + targetDocumentFormat.getName();
    LOGGER.info(msg);
    ProcessingLog log = new ProcessingLog();
    log.setSource(DocumentTransformation.class.getSimpleName());
    log.setMsg(msg);

    DocumentBody targetDocumentBody = new DocumentBody();
    targetDocumentBody.setBody(sourceDocumentBody.getBody());
    targetDocumentBody.setDocumentFormat(targetDocumentFormat);
    targetDocumentBody.setVersion(sourceDocumentBody.getVersion());
    targetDocumentBody.getProcessingLogs().add(log);
    return targetDocumentBody;
  }
}
