package pl.softfly.integ.doc.validation.business;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;


/**
 * Check if the document is correct with business validation rules e.g. REGEX of id.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentValidationBusinessBean implements DocumentValidationBusiness {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentValidationBusinessBean.class.getName());

  protected DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  /**
   * {@inheritDoc}
   */
  @Override
  public List<DocumentFormat> getSupported(DocumentBusinessType documentBusinessType) {
    List<DocumentFormat> documentFormats = new LinkedList<>();
    documentFormats.add(getDocumentFormatRepository().newInvoice2());
    return documentFormats;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<?> validate(String inputText) {
    Objects.requireNonNull(inputText);

    if ("BusinessNotValid".equals(inputText)) {
      LOGGER.info("Business not valid");
      List<Object> errorList = new LinkedList<>();
      errorList.add("BusinessNotValid");
      return errorList;
    } else {
      LOGGER.info("Business valid");
      return null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<?> validate(DocumentBody documentBody) {
    Objects.requireNonNull(documentBody);
    return validate(documentBody.getBody());
  }

  /**
   * Merge errors from {@link #validate(DocumentBody)} to {@link DocumentHeader}.
   */
  @Override
  public List<?> validate(DocumentHeader documentHeader) {
    Objects.requireNonNull(documentHeader);

    List<Object> errorList = new LinkedList<>();
    for (DocumentBody body : documentHeader.getBodies()) {
      @SuppressWarnings("unchecked")
      List<Object> bodyErrorList = (List<Object>) validate(body);
      if (CollectionUtils.isNotEmpty(bodyErrorList)) {
        errorList.addAll(bodyErrorList);
      }
    }

    return errorList.isEmpty() ? null : errorList;
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }

}
