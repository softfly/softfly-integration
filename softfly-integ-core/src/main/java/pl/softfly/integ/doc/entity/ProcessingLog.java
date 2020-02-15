package pl.softfly.integ.doc.entity;

import java.util.Date;

/**
 * The log describes the document processing steps
 */
public class ProcessingLog {

  private Integer id;

  private Date createdAt = new Date();

  /**
   * Place where the log was created.
   */
  private String source;

  /**
   * Message
   */
  private String msg;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
