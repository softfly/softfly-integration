package pl.softfly.oipf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table
public class EndpointWebService extends Endpoint {

    @Column
    private String host;

    @Column
    private Short port;

    @Column
    private String path;

}
