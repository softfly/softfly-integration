package pl.softfly.integ.doc.validation.schema;

import java.util.List;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Check if the document has the correct message structure e.g. XML, EDI.
 *
 * @author Grzegorz Ziemski
 */
public interface DocumentValidationSchema {

  List<?> validate(String inputText);

  List<?> validate(DocumentBody documentBody);

  List<?> validate(DocumentHeader documentHeader);

}
