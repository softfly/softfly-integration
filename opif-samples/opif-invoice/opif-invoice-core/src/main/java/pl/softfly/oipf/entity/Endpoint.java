package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Endpoint {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private DictDocumentFormat dictDocumentFormat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public DictDocumentFormat getDictDocumentFormat() {
        return dictDocumentFormat;
    }

    public void setDictDocumentFormat(DictDocumentFormat dictDocumentFormat) {
        this.dictDocumentFormat = dictDocumentFormat;
    }

}
