package pl.softfly.integ.doc.validation.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.DocumentValidation;
import pl.softfly.integ.doc.entity.ProcessingLog;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;
import pl.softfly.integ.doc.validation.schema.DocumentValidationSchema;


public class DocumentValidationBusinessBean implements DocumentValidationBusiness {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentValidationBusinessBean.class.getName());

  private DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  @Override
  public List<DocumentFormat> getSupported(List<DocumentBusinessType> documentBusinessType) {
    List<DocumentFormat> documentFormats = new LinkedList<>();
    documentFormats.add(getDocumentFormatRepository().newInvoice2());
    return documentFormats;
  }

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
    if ("BusinessNotValid".equals(documentBodyIn.getBody())) {
      String msg = "Business not valid";
      log.setMsg(msg);
      LOGGER.info(msg);
      DocumentValidation documentValidation = new DocumentValidation();
      documentValidation.setMsg(msg);
      documentBodyOut.getValidations().add(documentValidation);
    } else {
      String msg = "Business valid";
      log.setMsg(msg);
      LOGGER.info(msg);
    }
    documentBodyOut.getProcessingLogs().add(log);
    return documentBodyOut;
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }
}
