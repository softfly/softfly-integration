package pl.softfly.integ.doc.validation.schema;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Check if the document has the correct message structure e.g. XML, EDI.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentValidationSchemaBean implements DocumentValidationSchema {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentValidationSchemaBean.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public List<?> validate(String inputText) {
    Objects.requireNonNull(inputText);

    if ("SchemeNotValid".equals(inputText)) {
      LOGGER.info("Scheme not valid");
      List<Object> errorList = new LinkedList<>();
      errorList.add("SchemeNotValid");
      return errorList;
    } else {
      LOGGER.info("Scheme valid");
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

}
