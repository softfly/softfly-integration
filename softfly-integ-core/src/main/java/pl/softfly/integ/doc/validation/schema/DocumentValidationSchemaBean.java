package pl.softfly.integ.doc.validation.schema;

import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.DocumentValidation;
import pl.softfly.integ.doc.entity.ProcessingLog;


/**
 * Check if the document has the correct message structure e.g. XML, EDI.
 */
public class DocumentValidationSchemaBean implements DocumentValidationSchema {

  private static final Logger LOGGER = Logger.getLogger(DocumentValidationSchema.class.getName());

  /**
   * Merge errors from {@link #validate(DocumentBody)} to {@link DocumentHeader}.
   */
  @Override
  public DocumentHeader validate(final DocumentHeader documentHeaderIn) {
    Objects.requireNonNull(documentHeaderIn);
    DocumentHeader documentHeaderOut = new DocumentHeader();

    for (DocumentBody documentBodyIn : documentHeaderIn.getBodies()) {
      documentHeaderOut.getBodies().add(validate(documentBodyIn));
    }
    return documentHeaderOut;
  }

  protected DocumentBody validate(final DocumentBody documentBodyIn) {
    Objects.requireNonNull(documentBodyIn);
    final DocumentBody documentBodyOut = new DocumentBody();
    documentBodyOut.setId(documentBodyIn.getId());

    ProcessingLog log = new ProcessingLog();
    log.setSource(DocumentValidationSchema.class.getSimpleName());
    if ("SchemeNotValid".equals(documentBodyIn.getBody())) {
      String msg = "Scheme not valid";
      log.setMsg(msg);
      LOGGER.info(msg);
      DocumentValidation documentValidation = new DocumentValidation();
      documentValidation.setMsg(msg);
      documentBodyOut.getValidations().add(documentValidation);
    } else {
      String msg = "Scheme valid";
      log.setMsg(msg);
      LOGGER.info(msg);
    }
    documentBodyOut.getProcessingLogs().add(log);
    return documentBodyOut;
  }
}
