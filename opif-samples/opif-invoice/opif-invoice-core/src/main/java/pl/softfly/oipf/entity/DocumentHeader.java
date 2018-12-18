package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@XmlRootElement
@Table
public class DocumentHeader implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Participant sender;

    @OneToMany
    private List<Participant> recipients;

    @OneToMany
    private List<DocumentBody> bodies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Participant getSender() {
        return sender;
    }

    public void setSender(Participant sender) {
        this.sender = sender;
    }

    public List<Participant> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Participant> recipients) {
        this.recipients = recipients;
    }

    public List<DocumentBody> getBodies() {
        if (bodies == null) {
            bodies = new LinkedList<DocumentBody>();
        }
        return bodies;
    }

    public void setBodies(List<DocumentBody> bodies) {
        this.bodies = bodies;
    }

}
