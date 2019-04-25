package pl.softfly.oipf.document.validation.schema;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.List;

public interface DocumentValidationSchema {

    List<?> validate(DocumentBody documentBody);

    List<?> validate(DocumentHeader documentHeader);

}
