package pl.softfly.opif.document.create;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public class TransformStringToDocumentBean implements TransformStringToDocument {

    @Override
    public DocumentBody transformToBody(String inputDocument) {
        DocumentBody documentBody = new DocumentBody();
        documentBody.setBody(inputDocument);
        return documentBody;
    }

    @Override
    public DocumentHeader transformToHeader(String inputDocument) {
        DocumentBody documentBody = new DocumentBody();
        documentBody.setBody(inputDocument);

        DocumentHeader header = new DocumentHeader();
        header.getBodies().add(documentBody);

        return header;
    }

}
