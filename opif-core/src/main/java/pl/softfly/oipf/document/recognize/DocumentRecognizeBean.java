package pl.softfly.oipf.document.recognize;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentBusinessType;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.Objects;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class DocumentRecognizeBean implements DocumentRecognize {

    private final static Logger LOGGER = Logger.getLogger(DocumentRecognizeBean.class.getName());

    @Override
    public DictDocumentFormat recognize(DocumentBody documentBody) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentBody);
        
        LOGGER.info(LoggerUtil.end());
        if ("NotRecognized".equals(documentBody.getBody())) {
        	return null;
        } else {
        	return newDictDocumentFormat();
        }
    }
        
    protected DictDocumentFormat dictDocumentFormat = null;
    
    protected DictDocumentFormat newDictDocumentFormat() {
    	if (dictDocumentFormat==null) {
            dictDocumentFormat = new DictDocumentFormat();
            dictDocumentFormat.setName("G2_INVOICE_3.0");
            dictDocumentFormat.setDocumentBusinessType(newDocumentBusinessType());
    	}
        return dictDocumentFormat;
    }
    
    protected DocumentBusinessType documentBusinessType=null;  
    
    protected DocumentBusinessType newDocumentBusinessType() {
    	if (documentBusinessType==null) {
    		documentBusinessType = new DocumentBusinessType();
    		documentBusinessType.setName("INVOICE");
    	}
    	return documentBusinessType;
    }



}
