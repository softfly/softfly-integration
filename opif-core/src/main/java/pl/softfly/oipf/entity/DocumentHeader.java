package pl.softfly.oipf.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    private Set<DocumentBusinessType> documentBusinessType;

    @OneToMany
    private List<DocumentBody> bodies;
    
    private DocumentStatus status;

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

	public Set<DocumentBusinessType> getDocumentBusinessType() {
		if (documentBusinessType==null) {
			documentBusinessType = new LinkedHashSet<DocumentBusinessType>();
		}
		return documentBusinessType;
	}

	public void setDocumentBusinessType(Set<DocumentBusinessType> documentBusinessType) {
		this.documentBusinessType = documentBusinessType;
	}

	public List<DocumentBody> getBodies() {
		if (bodies==null) {
			bodies=new LinkedList<DocumentBody>();
		}
		return bodies;
	}

	public void setBodies(List<DocumentBody> bodies) {
		this.bodies = bodies;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}
	
}
