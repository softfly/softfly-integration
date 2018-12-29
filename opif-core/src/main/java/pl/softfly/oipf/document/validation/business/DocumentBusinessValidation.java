package pl.softfly.oipf.document.validation.business;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.List;

public interface DocumentBusinessValidation {

    List<?> valid(DocumentBody documentBody);

    List<?> valid(DocumentHeader documentHeader);

}
