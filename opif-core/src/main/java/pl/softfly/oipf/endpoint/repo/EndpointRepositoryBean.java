package pl.softfly.oipf.endpoint.repo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBusinessType;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;

public class EndpointRepositoryBean {

	/**
	 * Find supported endpoints where
     * participant.endpoint.DictDocumentFormat IN documentFormatSet
	 */
    public List<Endpoint> findEndpointsByDocumentFormat(Participant recipient, Set<DictDocumentFormat> documentFormatSet) {
        return Arrays.asList(newEndpoint());
    }

	/**
	 * Find the Endpoints where
	 * participant.endpoint.documentBusinessType = documentBusinessType
	 */
    public List<Endpoint> findEndpointsByDocumentBusinessTypes(Participant recipient, Set<DocumentBusinessType> documentBusinessTypes) {
		return Arrays.asList(newEndpoint());
	}

    protected Endpoint newEndpoint() {
        Endpoint e = new Endpoint();
        e.setDictDocumentFormat(newDictDocumentFormat());
        return e;
    }

	protected DictDocumentFormat newDictDocumentFormat() {
		DictDocumentFormat documentFormat = new DictDocumentFormat();
		documentFormat.setName("G2_INVOICE_3.0");
		documentFormat.setDocumentBusinessType(newDocumentBusinessType());
		return documentFormat;
	}

	protected DocumentBusinessType newDocumentBusinessType() {
		DocumentBusinessType documentBusinessType = new DocumentBusinessType();
		documentBusinessType.setName("INVOICE");
		return documentBusinessType;
	}

}
