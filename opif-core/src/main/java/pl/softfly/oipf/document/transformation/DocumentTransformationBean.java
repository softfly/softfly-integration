package pl.softfly.oipf.document.transformation;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;
import pl.softfly.oipf.utils.LoggerUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.collections4.MultiValuedMap;

public class DocumentTransformationBean implements DocumentTransformation {

    private final static Logger LOGGER = Logger.getLogger(DocumentTransformationBean.class.getName());

	@Override
	public MultiValuedMap<DictDocumentFormat, DictDocumentFormat> filterSupported(MultiValuedMap<DictDocumentFormat, DictDocumentFormat> toCheck) {
		LOGGER.info(LoggerUtil.start());
		Objects.requireNonNull(toCheck);
		//TODO Remove unsupported from map
		LOGGER.info(LoggerUtil.end());
		return toCheck;
	}

	/*
    @Override
    public DocumentBody transform(DocumentHeader sourceDocumentHeader, DictDocumentFormat targetDocumentFormat) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(sourceDocumentHeader);
        Objects.requireNonNull(targetDocumentFormat);

        DocumentBody targetDocumentBody = new DocumentBody();
        targetDocumentBody.setDocumentHeader(sourceDocumentHeader);
        LOGGER.info(LoggerUtil.end());
        return targetDocumentBody;
    }*/

    @Override
    public DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat) {
    	LOGGER.info(LoggerUtil.start());
        Objects.requireNonNull(sourceDocumentBody);
        Objects.requireNonNull(targetDocumentFormat);

        DocumentBody targetDocumentBody = new DocumentBody();
        targetDocumentBody.setDocumentHeader(sourceDocumentBody.getDocumentHeader());
        LOGGER.info(LoggerUtil.end());
        return targetDocumentBody;
    }

}
