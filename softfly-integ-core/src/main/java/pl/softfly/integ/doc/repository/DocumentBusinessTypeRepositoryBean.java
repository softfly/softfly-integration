package pl.softfly.integ.doc.repository;

import java.util.HashMap;
import java.util.Map;
import pl.softfly.integ.doc.entity.DocumentBusinessType;


/**
 * Repository.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentBusinessTypeRepositoryBean {

  protected static final Map<String, DocumentBusinessType> prototypes = new HashMap<>();

  public DocumentBusinessType getInvoice() {
    return getDocumentBusinessType("INVOICE");
  }

  protected DocumentBusinessType getDocumentBusinessType(String name) {
    DocumentBusinessType documentBusinessType = prototypes.get(name);
    if (documentBusinessType == null) {
      documentBusinessType = new DocumentBusinessType();
      documentBusinessType.setName(name);
      prototypes.put(name, documentBusinessType);
    }
    return documentBusinessType;
  }

}
