package pl.softfly.integ.doc.parser;

import java.util.List;
import java.util.Set;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;

/**
 * Parse the document to POJO (enriches with attributes, recipients).
 */
public interface DocumentParser {

  List<DocumentFormat> getSupported(Set<DocumentBusinessType> documentBusinessType);

  /**
   * Parse the document to POJO (enriches with attributes, recipients).
   */
  DocumentHeader parse(DocumentHeader documentHeader);

}
