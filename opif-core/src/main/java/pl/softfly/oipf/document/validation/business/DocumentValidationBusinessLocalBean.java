package pl.softfly.oipf.document.validation.business;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class DocumentValidationBusinessLocalBean {

    private final static Logger LOGGER = Logger.getLogger(DocumentValidationBusinessLocalBean.class.getName());

    protected DocumentValidationBusiness documentValidationBusiness;
    
    //@Override
    public List<?> validate(DocumentBody documentBody) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentBody);
        
        LOGGER.info(LoggerUtil.end());
        if ("NotBusinessValidated".equals(documentBody.getBody())) {
        	List<Object> errorList = new LinkedList<Object>();
        	errorList.add("NotBusinessValidated");
        	return errorList;
        } else {
        	return null;
        }
    }

    //@Override
    public List<?> validate(DocumentHeader documentHeader) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentHeader);
        
        documentValidationBusiness.findSupportedDocumentFormat(documentHeader);
        
        List<Object> errorList = new LinkedList<Object>();
        for (DocumentBody body: documentHeader.getBodies()) {
        	List<Object> bodyErrorList = (List<Object>) validate(body);
        	if (bodyErrorList != null && !bodyErrorList.isEmpty()) {
        		errorList.addAll(validate(body));
        	}
        }
        
        LOGGER.info(LoggerUtil.end());
        return errorList.isEmpty() ? null : errorList;
    }

}
