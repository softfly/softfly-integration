package pl.softfly.oipf.document.validation.scheme;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.List;

public interface DocumentValidationScheme {

    List<?> valid(DocumentBody documentBody);

    List<?> valid(DocumentHeader documentHeader);

}
