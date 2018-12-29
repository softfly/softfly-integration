package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table
public class DictDocumentFormat implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    private DocumentBusinessType documentBusinessType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentBusinessType getDocumentBusinessType() {
        return documentBusinessType;
    }

    public void setDocumentBusinessType(DocumentBusinessType documentBusinessType) {
        this.documentBusinessType = documentBusinessType;
    }

}
