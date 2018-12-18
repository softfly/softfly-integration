package pl.softfly.oipf.document.recognize;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.logging.Logger;

public interface DocumentRecognize {

    DictDocumentFormat recognize(DocumentBody documentBody);
    
    DocumentHeader enrichRecognize(DocumentHeader documentHeader);

}
