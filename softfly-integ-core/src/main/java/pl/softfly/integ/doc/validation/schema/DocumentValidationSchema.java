package pl.softfly.integ.doc.validation.schema;

import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Check if the document has the correct message structure e.g. XML, EDI.
 */
public interface DocumentValidationSchema {

  DocumentHeader validate(DocumentHeader documentHeader);

}
