package pl.softfly.integ.doc.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.utils.ObjectMapperFactory;

/**
 * Adds logging request/response.
 */
public class LoggingDocumentParserBean implements DocumentParser {

  private static final Logger LOGGER = Logger.getLogger(LoggingDocumentParserBean.class.getName());

  private DocumentParser documentParser;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggingDocumentParserBean(DocumentParser documentParser) {
    this.documentParser = documentParser;
  }

  @Override
  public List<DocumentFormat> getSupported(Set<DocumentBusinessType> documentBusinessType) {
    try {
      LOGGER.info(
          "Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentBusinessType));
      List<DocumentFormat> output = documentParser.getSupported(documentBusinessType);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DocumentHeader parse(DocumentHeader documentHeader) {
    try {
      LOGGER
          .info("Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentHeader));
      DocumentHeader output = documentParser.parse(documentHeader);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return documentParser.getClass().getName();
  }
}
