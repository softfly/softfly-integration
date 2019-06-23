package pl.softfly.integ.doc.parser;

import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;

/**
 * Parse the document to POJO (needed attributes, recipients).
 *
 * @author Grzegorz Ziemski
 */
public interface DocumentParser {

  DocumentBody parse(DocumentBody documentBody);

  DocumentBody parse(DocumentHeader documentHeader);

}
