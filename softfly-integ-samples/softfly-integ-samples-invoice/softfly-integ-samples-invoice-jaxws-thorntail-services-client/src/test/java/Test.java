import java.util.LinkedList;
import java.util.List;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.samples.invoice.jaxws.doc.validation.business.DocumentValidationBusiness;
import pl.softfly.integ.samples.invoice.jaxws.doc.validation.business.DocumentValidationBusinessService;

public class Test {

  private static DocumentValidationBusiness documentValidationBusiness = new DocumentValidationBusiness();

  private static DocumentValidationBusinessService documentValidationBusinessService =
      documentValidationBusiness.getDocumentValidationBusinessServicePort();

  public static void main(String[] args) {
    List<DocumentBusinessType> list = new LinkedList<>();
    list.add(new DocumentBusinessType());
    System.out.print(documentValidationBusinessService.getSupported(list));
  }

}
