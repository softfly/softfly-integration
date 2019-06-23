package pl.softfly.integ.doc.entity;

import java.io.Serializable;

/**
 * The content of the document in a different {@link DocumentFormat}.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentBody implements Serializable {

  private Integer id;

  private DocumentFormat documentFormat;

  private DocumentHeader documentHeader;

  private String body;

  private Integer version;

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

  public DocumentHeader getDocumentHeader() {
    return documentHeader;
  }

  public void setDocumentHeader(DocumentHeader documentHeader) {
    this.documentHeader = documentHeader;
  }

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

}
