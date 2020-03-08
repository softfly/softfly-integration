package pl.softfly.integ.samples.invoice.jaxws.doc.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;


public class DocumentParserBean implements pl.softfly.integ.doc.parser.DocumentParser {

  private DocumentParser documentParser = new DocumentParser();

  private DocumentParserService documentParserService =
      documentParser.getDocumentParserServicePort();

  @Override
  public List<DocumentFormat> getSupported(Set<DocumentBusinessType> documentBusinessType) {
    return documentParserService.getSupported(new LinkedList<>(documentBusinessType));
  }

  @Override
  public DocumentHeader parse(DocumentHeader documentHeader) {
    return documentParserService.parse(documentHeader);
  }
}
