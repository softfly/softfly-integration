package pl.softfly.oipf.endpoint;

import pl.softfly.oipf.endpoint.entity.DocumentShipment;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItemStatus;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentStatus;
import pl.softfly.oipf.endpoint.repo.EndpointRepositoryBean;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class SimpleEndpointBean implements pl.softfly.oipf.endpoint.Endpoint {

    private final static Logger LOGGER = Logger.getLogger(SimpleEndpointBean.class.getName());

    protected EndpointRepositoryBean endpointRepository = new EndpointRepositoryBean();


    @Override
    public DocumentShipment send(DocumentHeader documentHeader, Participant recipient) {
    	DocumentShipment shipment = prepareShipment(documentHeader, recipient);
    	return send(shipment);
    }

    @Override
    public DocumentShipment send(DocumentShipment shipment) {
    	LOGGER.info(LoggerUtil.start());
    	Objects.requireNonNull(shipment);

    	shipment.setStatus(DocumentShipmentStatus.SENDING);

    	boolean end=false;
        for (DocumentShipmentItem item : shipment.getItems()) {
            if (!end) {
                if (send(item.getDocumentBody(), item.getEndpoint())) {
                	end = true;
                	item.setStatus(DocumentShipmentItemStatus.SENT);
                } else {
                	item.setStatus(DocumentShipmentItemStatus.ERROR);
                }
            } else {
            	shipment.getItems().remove(item);
            }
        }

        if (end) {
        	shipment.setStatus(DocumentShipmentStatus.SENT);
        } else {
        	shipment.setStatus(DocumentShipmentStatus.ERROR);
        }

        LOGGER.info(LoggerUtil.end());
        return shipment;
    }

    @Override
    public boolean send(DocumentBody documentBody, pl.softfly.oipf.entity.Endpoint endpoint) {
        LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(documentBody);
        Objects.requireNonNull(endpoint);
        LOGGER.info("Sent documentBody to endpoint");
        LOGGER.info(LoggerUtil.end());
        return true;
    }

	public EndpointRepositoryBean getEndpointRepository() {
		return endpointRepository;
	}

	public void setEndpointRepository(EndpointRepositoryBean endpointRepository) {
		this.endpointRepository = endpointRepository;
	}

}
