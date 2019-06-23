package pl.softfly.integ.doc.recognize;

import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;


/**
 * Recognize the document format e.g. XML, EDIFACT, Invoice.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentRecognizeFormatBean implements DocumentRecognizeFormat {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentRecognizeFormatBean.class.getName());

  protected DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  /**
   * {@inheritDoc}
   */
  @Override
  public DocumentFormat recognize(String inputBody) {
    Objects.requireNonNull(inputBody);

    if ("NotRecognized".equals(inputBody)) {
      LOGGER.info("Not Recognized");
      return null;
    } else {
      DocumentFormat documentFormat = getDocumentFormatRepository().newInvoice1();
      LOGGER.info("Recognized " + documentFormat.getName());
      return documentFormat;
    }
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }

}
