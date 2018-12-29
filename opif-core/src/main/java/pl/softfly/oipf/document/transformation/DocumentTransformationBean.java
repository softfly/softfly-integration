package pl.softfly.oipf.document.transformation;

import org.apache.commons.lang3.StringUtils;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

import java.util.logging.Logger;

public class DocumentTransformationBean implements DocumentTransformation {

    private final static Logger LOGGER = Logger.getLogger(DocumentTransformationBean.class.getName());

    @Override
    public DocumentBody transform(DocumentHeader sourceDocumentHeader, DictDocumentFormat targetDocumentFormat) {
        LOGGER.info(StringUtils.center("START", 30, "="));
        DocumentBody targetDocumentBody = new DocumentBody();
        targetDocumentBody.setDocumentHeader(sourceDocumentHeader);
        LOGGER.info(StringUtils.center("END", 30, "="));
        return targetDocumentBody;
    }

    @Override
    public DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat) {
        LOGGER.info(StringUtils.center("START", 30, "="));
        DocumentBody targetDocumentBody = new DocumentBody();
        targetDocumentBody.setDocumentHeader(sourceDocumentBody.getDocumentHeader());
        LOGGER.info(StringUtils.center("END", 30, "="));
        return targetDocumentBody;
    }

}
