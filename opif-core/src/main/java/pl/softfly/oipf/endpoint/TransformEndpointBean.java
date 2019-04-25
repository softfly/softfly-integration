package pl.softfly.oipf.endpoint;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import pl.softfly.oipf.document.transformation.DocumentTransformationBean;
import pl.softfly.oipf.endpoint.entity.DocumentShipment;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentItemStatus;
import pl.softfly.oipf.endpoint.entity.DocumentShipmentStatus;
import pl.softfly.oipf.endpoint.repo.EndpointRepositoryBean;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.*;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Adds the possibility of transformation 
 * from format A to format B by {@link DocumentTransformation}
 */
public class TransformEndpointBean extends SimpleEndpointBean {

    private final static Logger LOGGER = Logger.getLogger(TransformEndpointBean.class.getName());

    protected DocumentTransformationBean documentTransformation = new DocumentTransformationBean();


    @Override
    public DocumentShipment send(DocumentShipment shipment) {
    	LOGGER.info(LoggerUtil.start());
    	Objects.requireNonNull(shipment);

    	shipment.setStatus(DocumentShipmentStatus.SENDING);

    	boolean end=false;
        for (DocumentShipmentItem item : shipment.getItems()) {
        	DocumentBody body = documentTransformation.transform(item.getDocumentBody(), item.getEndpoint().getDictDocumentFormat());
            if (!end) {
                if (send(body, item.getEndpoint())) {
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

}
