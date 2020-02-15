package pl.softfly.integ.doc.recognize;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.ProcessingLog;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;


public class DocumentRecognizeFormatBean implements DocumentRecognizeFormat {

  private static final Logger LOGGER =
      Logger.getLogger(DocumentRecognizeFormatBean.class.getName());

  private DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  @Override
  public DocumentHeader recognize(final DocumentHeader documentHeaderIn) {
    Objects.requireNonNull(documentHeaderIn);
    final DocumentHeader documentHeaderOut = new DocumentHeader();

    for (DocumentBody documentBodyIn : documentHeaderIn.getBodies()) {
      DocumentBody documentBodyOut = recognize(documentBodyIn);
      documentHeaderOut.getBodies().add(documentBodyOut);
      mergeAllDocumentBusinessType(documentHeaderOut, documentBodyOut);
    }
    return documentHeaderOut;
  }

  protected DocumentBody recognize(final DocumentBody documentBodyIn) {
    Objects.requireNonNull(documentBodyIn);
    Objects.requireNonNull(documentBodyIn.getBody());
    final DocumentBody documentBodyOut = new DocumentBody();
    documentBodyOut.setId(documentBodyIn.getId());

    ProcessingLog log = new ProcessingLog();
    log.setSource(DocumentRecognizeFormat.class.getSimpleName());
    if ("NotRecognized".equals(documentBodyIn.getBody())) {
      String msg = "Not Recognized";
      log.setMsg(msg);
      LOGGER.info(msg);
    } else {
      DocumentFormat documentFormat = getDocumentFormatRepository().newInvoice1();
      documentBodyOut.setDocumentFormat(documentFormat);
      String msg = "Recognized " + documentFormat.getName();
      log.setMsg(msg);
      LOGGER.info(msg);
    }
    documentBodyOut.getProcessingLogs().add(log);
    return documentBodyOut;
  }

  /**
   * Merge of {@link DocumentBusinessType} from {@link DocumentBody}.documentFormat.documentBusinessType
   * to {@link DocumentHeader#documentBusinessType}.
   */
  protected void mergeAllDocumentBusinessType(DocumentHeader documentHeader,
      DocumentBody documentBody) {
    DocumentFormat documentFormat = documentBody.getDocumentFormat();
    if (documentFormat != null) {
      DocumentBusinessType documentBusinessType = documentFormat.getDocumentBusinessType();
      if (documentBusinessType != null) {
        Set<DocumentBusinessType> documentBusinessTypes;
        if (documentHeader.getDocumentBusinessType() != null) {
          documentBusinessTypes = documentHeader.getDocumentBusinessType();
        } else {
          documentBusinessTypes = new HashSet<>();
          documentHeader.setDocumentBusinessType(documentBusinessTypes);
        }
        documentBusinessTypes.add(documentBusinessType);
      }
    }
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }

}
