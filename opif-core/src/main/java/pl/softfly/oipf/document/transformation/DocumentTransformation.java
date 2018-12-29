package pl.softfly.oipf.document.transformation;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentTransformation {

    DocumentBody transform(DocumentHeader sourceDocumentHeader, DictDocumentFormat targetDocumentFormat);

    DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat);

}
