package pl.softfly.integ.doc.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.ProcessingLog;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;
import pl.softfly.integ.doc.repository.DocumentHeaderRepositoryBean;
import pl.softfly.integ.entity.Participant;

public class DocumentParserBean implements DocumentParser {

  private static final Logger LOGGER = Logger.getLogger(DocumentParserBean.class.getName());

  protected DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  protected DocumentHeaderRepositoryBean documentHeaderRepository =
      new DocumentHeaderRepositoryBean();

  @Override
  public List<DocumentFormat> getSupported(Set<DocumentBusinessType> documentBusinessType) {
    return Arrays.asList(getDocumentFormatRepository().newInvoice3());
  }

  @Override
  public DocumentHeader parse(DocumentHeader documentHeader) {
    try {
      Objects.requireNonNull(documentHeader);
      LOGGER.info("Parsed documentBody");
      DocumentHeader documentHeaderOut = new DocumentHeader();
      documentHeaderOut.setRecipients(Arrays.asList(newParticipant1()));

      DocumentBody documentBodyOut = new DocumentBody();
      documentBodyOut.setDocumentFormat(getDocumentFormatRepository().newPojo());
      documentBodyOut.setVersion(documentHeaderRepository.findCurrentVersion(documentHeader));

      ProcessingLog log = new ProcessingLog();
      log.setSource(DocumentParser.class.getSimpleName());
      log.setMsg("Parsed documentBody");

      documentHeaderOut.setBodies(Arrays.asList(documentBodyOut));
      return documentHeaderOut;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected Participant newParticipant1() {
    Participant participant = new Participant();
    participant.setId(1);
    participant.setName("Recipients1");
    return participant;
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }

  public DocumentHeaderRepositoryBean getDocumentHeaderRepository() {
    return documentHeaderRepository;
  }

  public void setDocumentHeaderRepository(DocumentHeaderRepositoryBean documentHeaderRepository) {
    this.documentHeaderRepository = documentHeaderRepository;
  }
}
