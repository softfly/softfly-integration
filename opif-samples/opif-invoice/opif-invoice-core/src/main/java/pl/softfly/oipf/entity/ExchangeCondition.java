package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table
public class ExchangeCondition implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private DictDocumentFormat documentFormat;

    @ManyToOne
    private Endpoint endpoint;

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

    public DictDocumentFormat getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(DictDocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

}
