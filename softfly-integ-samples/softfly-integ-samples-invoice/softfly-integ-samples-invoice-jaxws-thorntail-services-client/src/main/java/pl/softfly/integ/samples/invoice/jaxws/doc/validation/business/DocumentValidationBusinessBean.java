package pl.softfly.integ.samples.invoice.jaxws.doc.validation.business;

import java.util.List;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;

public class DocumentValidationBusinessBean
    implements pl.softfly.integ.doc.validation.business.DocumentValidationBusiness {

  private DocumentValidationBusiness documentValidationBusiness = new DocumentValidationBusiness();

  private DocumentValidationBusinessService documentValidationBusinessService =
      documentValidationBusiness.getDocumentValidationBusinessServicePort();

  @Override
  public List<DocumentFormat> getSupported(List<DocumentBusinessType> documentBusinessType) {
    return documentValidationBusinessService.getSupported(documentBusinessType);
  }

  @Override
  public DocumentHeader validate(DocumentHeader documentHeader) {
    return documentValidationBusinessService.validate(documentHeader);
  }

}
