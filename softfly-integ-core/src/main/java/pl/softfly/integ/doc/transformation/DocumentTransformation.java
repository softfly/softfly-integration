package pl.softfly.integ.doc.transformation;

import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentFormat;

/**
 * Transform the document to other format.
 *
 * @author Grzegorz Ziemski
 */
public interface DocumentTransformation {

  boolean isSupported(DocumentFormat sourceDocumentFormat, DocumentFormat targetDocumentFormat);

  DocumentBody transform(DocumentBody sourceDocumentBody, DocumentFormat targetDocumentFormat);

}
