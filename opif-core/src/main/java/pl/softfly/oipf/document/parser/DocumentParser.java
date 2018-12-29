package pl.softfly.oipf.document.parser;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentParser {

    DocumentBody parse(DocumentBody documentBody);

    DocumentHeader parse(DocumentHeader documentHeader);

}
