package pl.softfly.integ.doc.repository;

import java.util.HashMap;
import java.util.Map;
import pl.softfly.integ.doc.entity.DocumentFormat;


/**
 * Repository.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentFormatRepositoryBean {

  protected static final Map<String, DocumentFormat> prototypes = new HashMap<>();

  protected DocumentBusinessTypeRepositoryBean documentBusinessTypeRepository =
      new DocumentBusinessTypeRepositoryBean();

  public DocumentFormat newInvoice1() {
    return getDocumentFormat("INVOICE_1.0");
  }

  public DocumentFormat newInvoice2() {
    return getDocumentFormat("INVOICE_2.0");
  }

  public DocumentFormat newInvoice3() {
    return getDocumentFormat("INVOICE_3.0");
  }

  public DocumentFormat newPojo() {
    return getDocumentFormat("POJO");
  }

  protected DocumentFormat getDocumentFormat(String name) {
    DocumentFormat documentFormat = prototypes.get(name);
    if (documentFormat == null) {
      documentFormat = new DocumentFormat();
      documentFormat.setName(name);
      documentFormat.setDocumentBusinessType(getDocumentBusinessTypeRepository().getInvoice());
    }
    prototypes.put(name, documentFormat);
    return documentFormat;
  }

  public DocumentBusinessTypeRepositoryBean getDocumentBusinessTypeRepository() {
    return documentBusinessTypeRepository;
  }

  public void setDocumentBusinessTypeRepository(
      DocumentBusinessTypeRepositoryBean documentBusinessTypeRepository) {
    this.documentBusinessTypeRepository = documentBusinessTypeRepository;
  }


}
