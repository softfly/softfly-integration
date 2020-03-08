package pl.softfly.integ.doc.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The content of the document in a different {@link DocumentFormat}.
 */
public class DocumentBody implements Serializable {

  private Integer id;

  private DocumentFormat documentFormat;

  // TODO remove?
  // @XmlTransient
  // private DocumentHeader documentHeader;

  /**
   * Serialized content
   */
  private String body;

  /**
   * If the business content is changed, the version should go up. {@link DocumentBody} in the same
   * version only differ {@link DocumentFormat}.
   */
  private Integer version;

  private List<DocumentValidation> validations = new LinkedList<>();

  private List<ProcessingLog> processingLogs = new LinkedList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DocumentFormat getDocumentFormat() {
    return documentFormat;
  }

  public void setDocumentFormat(DocumentFormat documentFormat) {
    this.documentFormat = documentFormat;
  }

  /*
   * public DocumentHeader getDocumentHeader() { return documentHeader; }
   *
   * public void setDocumentHeader(DocumentHeader documentHeader) { this.documentHeader =
   * documentHeader; }
   */

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public List<ProcessingLog> getProcessingLogs() {
    return processingLogs;
  }

  public void setProcessingLogs(List<ProcessingLog> processingLogs) {
    this.processingLogs = processingLogs;
  }

  public List<DocumentValidation> getValidations() {
    return validations;
  }

  public void setValidations(List<DocumentValidation> validations) {
    this.validations = validations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DocumentBody documentBody = (DocumentBody) o;

    return new EqualsBuilder().append(id, documentBody.id).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }
}
