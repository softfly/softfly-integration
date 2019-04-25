package pl.softfly.oipf.document.recognize;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentRecognize {

    DictDocumentFormat recognize(DocumentBody documentBody);

}
