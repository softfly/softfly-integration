package pl.softfly.opif.document.repo;

import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentRepository {

	/**
	 * Create a DocumentHeader from the string
	 */
    public DocumentBody createDocumentBody(String inputDocument);

	/**
	 * Create a DocumentBody from the string
	 */
    public DocumentHeader createDocumentHeader(String inputDocument);

}
