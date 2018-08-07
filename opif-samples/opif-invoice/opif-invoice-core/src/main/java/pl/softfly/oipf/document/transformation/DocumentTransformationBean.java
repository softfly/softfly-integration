package pl.softfly.oipf.document.transformation;

import org.apache.commons.lang3.StringUtils;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;

import java.util.logging.Logger;

public class DocumentTransformationBean {

    private final static Logger LOGGER = Logger.getLogger(DocumentTransformationBean.class.getName());

    public DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat) {
        LOGGER.info(StringUtils.center("START", 30, "="));
        DocumentBody targetDocumentBody = new DocumentBody();
        targetDocumentBody.setDocumentHeader(sourceDocumentBody.getDocumentHeader());
        LOGGER.info(StringUtils.center("END", 30, "="));
        return targetDocumentBody;
    }

}
