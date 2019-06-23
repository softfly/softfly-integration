package pl.softfly.integ.doc.recognize;

import pl.softfly.integ.doc.entity.DocumentFormat;

/**
 * Recognize the document format e.g. XML, EDIFACT, Invoice.
 *
 * @author Grzegorz Ziemski
 */
public interface DocumentRecognizeFormat {

  DocumentFormat recognize(String inputBody);

}
