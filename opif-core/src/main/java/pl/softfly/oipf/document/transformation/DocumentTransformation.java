package pl.softfly.oipf.document.transformation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;

import pl.softfly.oipf.entity.DictDocumentFormat;
import pl.softfly.oipf.entity.DocumentBody;
import pl.softfly.oipf.entity.DocumentHeader;

public interface DocumentTransformation {

	MultiValuedMap<DictDocumentFormat, DictDocumentFormat> filterSupported(MultiValuedMap<DictDocumentFormat, DictDocumentFormat> toCheck);

    //DocumentBody transform(DocumentHeader sourceDocumentHeader, DictDocumentFormat targetDocumentFormat);

    DocumentBody transform(DocumentBody sourceDocumentBody, DictDocumentFormat targetDocumentFormat);

}
