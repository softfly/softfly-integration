package pl.softfly.oipf.document.recognize;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.logging.Logger;

public class DocumentRecognizeBean implements DocumentRecognize {

    private final static Logger LOGGER = Logger.getLogger(DocumentRecognizeBean.class.getName());

    @Override
    public DictDocumentFormat recognize(DocumentBody documentBody) {
        LoggerUtil.start(LOGGER);
        DictDocumentFormat documentFormat = new DictDocumentFormat();
        documentFormat.setName("G2_INVOICE_3.0");
        LoggerUtil.end(LOGGER);
        return documentFormat;
    }

    @Override
    public DocumentHeader enrichRecognize(DocumentHeader documentHeader) {
        for (DocumentBody body : documentHeader.getBodies()) {
            body.setDocumentFormat(recognize(body));
        }
        return documentHeader;
    }

}
