package pl.softfly.integ.doc.entity;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the pl.softfly.integ.doc.entity package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: pl.softfly.integ.doc.entity
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link DocumentBody }
   */
  public DocumentBody createDocumentBody() {
    return new DocumentBody();
  }

  /**
   * Create an instance of {@link DocumentValidation }
   */
  public DocumentValidation createDocumentValidation() {
    return new DocumentValidation();
  }

  /**
   * Create an instance of {@link DocumentBusinessType }
   */
  public DocumentBusinessType createDocumentBusinessType() {
    return new DocumentBusinessType();
  }

  /**
   * Create an instance of {@link DocumentFormat }
   */
  public DocumentFormat createDocumentFormat() {
    return new DocumentFormat();
  }

  /**
   * Create an instance of {@link ProcessingLog }
   */
  public ProcessingLog createProcessingLog() {
    return new ProcessingLog();
  }

}
