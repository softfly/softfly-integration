package pl.softfly.integ.endpoint.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.entity.Participant;

/**
 * Repository.
 */
public class EndpointRepositoryBean {

  protected static final Map<Participant, Map<DocumentFormat, Endpoint>> prototypes =
      new HashMap<>();
  private static int nextId = 1;
  private DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  public List<Endpoint> findBy(Participant participant, DocumentFormat documentFormat) {
    List<Endpoint> endpoints = new LinkedList<>();
    endpoints.add(getEndpoint(participant, documentFormat));
    return endpoints;
  }

  public List<Endpoint> findBy(Participant participant,
      Collection<DocumentBusinessType> documentBusinessTypes) {
    List<Endpoint> endpoints = new LinkedList<>();
    endpoints.add(newEndpoint3(participant));
    return endpoints;
  }

  protected Endpoint newEndpoint3(Participant participant) {
    return getEndpoint(participant, getDocumentFormatRepository().newInvoice3());
  }

  protected Endpoint getEndpoint(Participant participant, DocumentFormat documentFormat) {
    Map<DocumentFormat, Endpoint> p = prototypes.get(participant);
    if (Objects.nonNull(p)) {
      Endpoint e = p.get(documentFormat);
      if (Objects.nonNull(e)) {
        return e;
      }
    } else {
      p = new HashMap<>();
      prototypes.put(participant, p);
    }

    Endpoint e = newEndpoint(participant, documentFormat);
    p.put(documentFormat, e);

    return e;
  }

  protected Endpoint newEndpoint(Participant participant, DocumentFormat documentFormat) {
    Endpoint e = new Endpoint();
    e.setId(nextId++);
    e.setDocumentFormat(documentFormat);
    e.setParticipant(participant);
    return e;
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }
}
