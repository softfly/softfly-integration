package pl.softfly.oipf.document.recognize;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.logging.Logger;

public class DocumentRecognizeBean {

    private final static Logger LOGGER = Logger.getLogger(DocumentRecognizeBean.class.getName());

    public DictDocumentFormat recognize(DocumentBody documentBody) {
        LoggerUtil.start(LOGGER);
        DictDocumentFormat documentFormat = new DictDocumentFormat();
        documentFormat.setName("G2_INVOICE_3.0");
        LoggerUtil.end(LOGGER);
        return documentFormat;
    }

}
