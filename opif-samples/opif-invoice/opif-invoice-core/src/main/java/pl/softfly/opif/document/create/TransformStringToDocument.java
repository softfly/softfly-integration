package pl.softfly.opif.document.create;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface TransformStringToDocument {
	
    DocumentBody transformToBody(String inputDocument);

    DocumentHeader transformToHeader(String inputDocument);

}
