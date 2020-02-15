package pl.softfly.integ.doc.recognize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerDocumentRecognizeFormatBean implements DocumentRecognizeFormat {

  private static final Logger LOGGER =
      Logger.getLogger(LoggerDocumentRecognizeFormatBean.class.getName());

  private DocumentRecognizeFormat documentRecognizeFormat;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerDocumentRecognizeFormatBean(DocumentRecognizeFormat documentRecognizeFormat) {
    this.documentRecognizeFormat = documentRecognizeFormat;
  }

  @Override
  public DocumentHeader recognize(DocumentHeader documentHeader) {
    try {
      LOGGER.info(
          "Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentHeader));
      DocumentHeader output = documentRecognizeFormat.recognize(documentHeader);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return documentRecognizeFormat.getClass().getName();
  }
}
