package pl.softfly.oipf.endpoint;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import pl.softfly.oipf.endpoint.entity.DocumentShipmentItem;
import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.entity.Endpoint;
import pl.softfly.oipf.entity.Participant;
import pl.softfly.oipf.utils.LoggerUtil;

public class TransformDetermineEndpointsBean extends DetermineEndpointsBean {

    /**
     * Find supported endpoints where
     * 1) documentHeader.DocumentBusinessType == participant.endpoint.DictDocumentFormat.DocumentBusinessType
     * 2) {@link DocumentTransformation} support transformation
     * 	  from DocumentHeader.DocumentBody.DictDocumentFormat to Participant.Endpoint.DictDocumentFormat
     */
    @Override
    public DocumentShipment prepareShipment(DocumentHeader documentHeader, Participant recipient) {
    	LOGGER.info(LoggerUtil.start());
    	Objects.requireNonNull(documentHeader);
    	Objects.requireNonNull(recipient);

    	List<Endpoint> endpoints = getEndpointRepository().findEndpointsByDocumentBusinessTypes(recipient, documentHeader.getDocumentBusinessType());
    	MultiValuedMap<DictDocumentFormat, DictDocumentFormat> supportedTransformationMap = findSupportedTransformation(documentHeader, endpoints);

        DocumentShipment shipment = new DocumentShipment();
        for (DocumentBody documentBody : documentHeader.getBodies()) {
            for (Endpoint endpoint : endpoints) {
            	if (supportedTransformationMap.containsMapping(documentBody.getDocumentFormat(), endpoint.getDictDocumentFormat())) {
                	shipment.getItems().add(new DocumentShipmentItem(documentBody, endpoint));
                }
            }
        }

        LOGGER.info(LoggerUtil.end());
        return shipment;
    }

    /**
     * Find supported transformation by {@link DocumentTransformation}
     * from DocumentHeader.DocumentBody.DictDocumentFormat to Participant.Endpoint.DictDocumentFormat
     *
     * @return [DocumentHeader.DocumentBody.DictDocumentFormat][Participant.Endpoint.DictDocumentFormat]
     */
    protected MultiValuedMap<DictDocumentFormat, DictDocumentFormat>
    	findSupportedTransformation(DocumentHeader documentHeader, List<Endpoint> endpoints) {

        MultiValuedMap<DictDocumentFormat, DictDocumentFormat> possibleTransformationMap = new HashSetValuedHashMap<>(
                documentHeader.getBodies().size(), endpoints.size());
        for (Endpoint endpoint : endpoints) {
            for (DocumentBody body : documentHeader.getBodies()) {
                possibleTransformationMap.put(body.getDocumentFormat(), endpoint.getDictDocumentFormat());
            }
        }

        possibleTransformationMap = documentTransformation.filterSupported(possibleTransformationMap);

        return possibleTransformationMap;
    }

}
