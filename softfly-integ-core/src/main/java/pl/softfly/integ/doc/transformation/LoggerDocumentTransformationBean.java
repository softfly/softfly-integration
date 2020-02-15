package pl.softfly.integ.doc.transformation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerDocumentTransformationBean implements DocumentTransformation {

  private static final Logger LOGGER =
      Logger.getLogger(LoggerDocumentTransformationBean.class.getName());

  private DocumentTransformation documentTransformation;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerDocumentTransformationBean(DocumentTransformation documentTransformation) {
    this.documentTransformation = documentTransformation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSupported(DocumentFormat sourceDocumentFormat,
      DocumentFormat targetDocumentFormat) {
    try {
      LOGGER.info("Request to " + getClassName() + "\nsourceDocumentFormat:\n"
          + mapper.writeValueAsString(sourceDocumentFormat) + "\ntargetDocumentFormat:\n"
          + mapper.writeValueAsString(targetDocumentFormat));
      boolean output = documentTransformation
          .isSupported(sourceDocumentFormat, targetDocumentFormat);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DocumentBody transform(DocumentBody sourceDocumentBody,
      DocumentFormat targetDocumentFormat) {
    try {
      LOGGER.info("Request to " + getClassName() + "\nsourceDocumentBody:\n"
          + mapper.writeValueAsString(sourceDocumentBody) + "\ntargetDocumentFormat:\n"
          + mapper.writeValueAsString(targetDocumentFormat));
      DocumentBody output =
          documentTransformation.transform(sourceDocumentBody, targetDocumentFormat);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return DocumentTransformation.class.getName();
  }

}
