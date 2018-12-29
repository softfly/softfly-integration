package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table
public class DocumentBody implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private DictDocumentFormat documentFormat;

    @ManyToOne
    private DocumentHeader documentHeader;

    @Column
    private String body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DictDocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DictDocumentFormat documentFormat) {
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

}
