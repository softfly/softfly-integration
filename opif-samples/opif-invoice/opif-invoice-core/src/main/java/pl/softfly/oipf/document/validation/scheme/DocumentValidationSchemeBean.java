package pl.softfly.oipf.document.validation.scheme;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.List;
import java.util.logging.Logger;

public class DocumentValidationSchemeBean implements DocumentValidationScheme {

    private final static Logger LOGGER = Logger.getLogger(DocumentValidationSchemeBean.class.getName());

    @Override
    public List<?> valid(DocumentBody documentBody) {
        LoggerUtil.start(LOGGER);
        LoggerUtil.end(LOGGER);
        return null;
    }
    
    @Override
    public List<?> valid(DocumentHeader documentHeader) {
        LoggerUtil.start(LOGGER);
        LoggerUtil.end(LOGGER);
        return null;
    }

}
