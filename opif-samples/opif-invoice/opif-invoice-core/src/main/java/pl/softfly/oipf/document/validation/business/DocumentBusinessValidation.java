package pl.softfly.oipf.document.validation.business;

import java.util.List;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentBusinessValidation {
	
	List<?> valid(DocumentBody documentBody);
	
	List<?> valid(DocumentHeader documentHeader);

}
