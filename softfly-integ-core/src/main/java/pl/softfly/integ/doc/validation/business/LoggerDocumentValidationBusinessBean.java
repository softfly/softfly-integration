package pl.softfly.integ.doc.validation.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerDocumentValidationBusinessBean implements DocumentValidationBusiness {

  private static final Logger LOGGER = Logger.getLogger(DocumentValidationBusiness.class.getName());

  private DocumentValidationBusiness documentValidationBusiness;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerDocumentValidationBusinessBean(
      DocumentValidationBusiness documentValidationBusiness) {
    this.documentValidationBusiness = documentValidationBusiness;
  }

  @Override
  public List<DocumentFormat> getSupported(List<DocumentBusinessType> documentBusinessType) {
    try {
      LOGGER.info(
          "Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentBusinessType));
      List<DocumentFormat> output = documentValidationBusiness.getSupported(documentBusinessType);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DocumentHeader validate(DocumentHeader documentHeader) {
    try {
      LOGGER
          .info("Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentHeader));
      DocumentHeader output = documentValidationBusiness.validate(documentHeader);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return DocumentValidationBusiness.class.getName();
  }
}
