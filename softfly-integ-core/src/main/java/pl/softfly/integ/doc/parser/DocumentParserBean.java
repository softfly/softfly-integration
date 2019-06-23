package pl.softfly.integ.doc.parser;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;
import pl.softfly.integ.entity.Participant;


/**
 * Parse the document to POJO (needed attributes, recipients).
 *
 * @author Grzegorz Ziemski
 */
public class DocumentParserBean implements DocumentParser {

  private static final Logger LOGGER = Logger.getLogger(DocumentParserBean.class.getName());

  protected DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  /**
   * {@inheritDoc}
   */
  @Override
  public DocumentBody parse(DocumentBody sourceDocumentBody) {
    Objects.requireNonNull(sourceDocumentBody);
    LOGGER.info("Parsed documentBody");

    DocumentBody parsedBody = new DocumentBody();
    parsedBody.setBody(sourceDocumentBody.getBody());
    parsedBody.setDocumentHeader(sourceDocumentBody.getDocumentHeader());
    parsedBody.setDocumentFormat(getDocumentFormatRepository().newPojo());
    parsedBody.getDocumentHeader().setRecipients(Arrays.asList(newParticipant()));

    Integer version = sourceDocumentBody.getVersion() + 1;
    parsedBody.setVersion(version);
    parsedBody.getDocumentHeader().setVersion(version);

    return parsedBody;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DocumentBody parse(DocumentHeader documentHeader) {
    Objects.requireNonNull(documentHeader);
    DocumentBody sourceDocumentBody = documentHeader.getBodies().get(0);
    return parse(sourceDocumentBody);
  }

  protected Participant newParticipant() {
    return new Participant();
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }

}
