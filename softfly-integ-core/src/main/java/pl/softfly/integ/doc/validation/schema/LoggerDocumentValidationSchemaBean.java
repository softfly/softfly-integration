package pl.softfly.integ.doc.validation.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerDocumentValidationSchemaBean implements DocumentValidationSchema {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentValidationSchema.class.getName());

  private DocumentValidationSchema documentValidationSchema;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerDocumentValidationSchemaBean(DocumentValidationSchema documentValidationSchema) {
    this.documentValidationSchema = documentValidationSchema;
  }

  @Override
  public DocumentHeader validate(DocumentHeader documentHeader) {
    try {
      LOGGER
          .info("Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentHeader));
      DocumentHeader output = documentValidationSchema.validate(documentHeader);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return DocumentValidationSchema.class.getName();
  }
}
