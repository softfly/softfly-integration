package pl.softfly.opif.document.repo;

import java.util.Objects;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public class DocumentRepositoryBean implements DocumentRepository {

	/**
	 * {@inheritDoc}
	 */
    @Override
    public DocumentBody createDocumentBody(String inputDocument) {
    	Objects.requireNonNull(inputDocument);
    	
        DocumentBody documentBody = new DocumentBody();
        documentBody.setBody(inputDocument);
        
        DocumentHeader header = new DocumentHeader();
        documentBody.setDocumentHeader(header);
        header.getBodies().add(documentBody);
        
        return documentBody;
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public DocumentHeader createDocumentHeader(String inputDocument) {
    	Objects.requireNonNull(inputDocument);
        DocumentBody documentBody = createDocumentBody(inputDocument);

        return documentBody.getDocumentHeader();
    }

}
