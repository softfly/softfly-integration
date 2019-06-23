package pl.softfly.integ.doc.validation.business;

import java.util.List;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Check if the document is correct with business validation rules e.g. REGEX of id.
 *
 * @author Grzegorz Ziemski
 */
public interface DocumentValidationBusiness {

  List<DocumentFormat> getSupported(DocumentBusinessType documentBusinessType);

  List<?> validate(String inputText);

  List<?> validate(DocumentBody documentBody);

  List<?> validate(DocumentHeader documentHeader);

}
