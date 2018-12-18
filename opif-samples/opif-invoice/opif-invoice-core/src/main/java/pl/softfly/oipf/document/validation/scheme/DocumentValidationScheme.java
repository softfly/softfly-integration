package pl.softfly.oipf.document.validation.scheme;

import java.util.List;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentValidationScheme {
	
	List<?> valid(DocumentBody documentBody);
	
	List<?> valid(DocumentHeader documentHeader);

}
