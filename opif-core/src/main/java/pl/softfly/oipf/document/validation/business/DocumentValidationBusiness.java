package pl.softfly.oipf.document.validation.business;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.List;

public interface DocumentValidationBusiness {
	
	List<DictDocumentFormat> findSupportedDocumentFormat(DocumentHeader documentHeader);

    List<?> validate(DocumentBody documentBody);

    List<?> validate(DocumentHeader documentHeader);

}
