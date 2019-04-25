package pl.softfly.oipf.endpoint;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.endpoint.repo.EndpointRepositoryBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.utils.LoggerUtil;

public class DetermineEndpointsBean {

    private final static Logger LOGGER = Logger.getLogger(DetermineEndpointsBean.class.getName());

    protected EndpointRepositoryBean endpointRepository = new EndpointRepositoryBean();

    /**
     * Find supported endpoints where
     * documentHeader.documentBody.DictDocumentFormat == participant.endpoint.DictDocumentFormat
     */
    //@Override
    public List<DocumentShipmentItem> determineShipment(DocumentHeader documentHeader, Participant recipient) {
    	LOGGER.info(LoggerUtil.start());
    	Objects.requireNonNull(documentHeader);
    	Objects.requireNonNull(recipient);

        Set<DictDocumentFormat> documentFormatSet = new HashSet<DictDocumentFormat>();
        for (DocumentBody body : documentHeader.getBodies()) {
            documentFormatSet.add(body.getDocumentFormat());
        }

        List<Endpoint> endpoints = getEndpointRepository().findEndpointsByDocumentFormat(recipient, documentFormatSet);

        List<DocumentShipmentItem> items = new LinkedList<>();
        for (DocumentBody documentBody : documentHeader.getBodies()) {
            for (Endpoint endpoint : endpoints) {
                if (documentBody.getDocumentFormat().equals(endpoint.getDictDocumentFormat())) {
                	items.add(new DocumentShipmentItem(documentBody, endpoint));
                }
            }
        }

        LOGGER.info(LoggerUtil.end());
        return items;
    }

	public EndpointRepositoryBean getEndpointRepository() {
		return endpointRepository;
	}

	public void setEndpointRepository(EndpointRepositoryBean endpointRepository) {
		this.endpointRepository = endpointRepository;
	}
}
