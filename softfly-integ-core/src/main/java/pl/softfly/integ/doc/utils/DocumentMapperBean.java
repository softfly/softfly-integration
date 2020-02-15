package pl.softfly.integ.doc.utils;

import java.util.Set;
import java.util.stream.Collectors;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

public class DocumentMapperBean {

  public Participant cloneParticipant(final Participant source) {
    Participant target = new Participant();
    target.setId(source.getId());
    target.setName(source.getName());
    target.setEndpoints(
        source.getEndpoints().stream().map(this::cloneEndpoint).collect(Collectors.toList()));
    return target;
  }

  public Endpoint cloneEndpoint(final Endpoint source) {
    Endpoint target = new Endpoint();
    target.setDocumentFormat(cloneDocumentFormat(source.getDocumentFormat()));
    target.setId(source.getId());
    target.setName(source.getName());
    return target;
  }

  public ShipmentOutgoing cloneShipmentOutgoing(final ShipmentOutgoing source) {
    ShipmentOutgoing target = new ShipmentOutgoing();
    target.setId(source.getId());
    target.setEndpoint(source.getEndpoint());
    target.setDocumentBody(cloneShortDocumentBody(source.getDocumentBody()));
    target.setStatus(source.getStatus());
    target.setCost(source.getCost());
    return target;
  }

  public DocumentBody cloneDocumentBody(final DocumentBody source) {
    DocumentBody target = new DocumentBody();
    target.setId(source.getId());
    target.setBody(source.getBody());
    target.setVersion(source.getVersion());
    if (source.getDocumentFormat() != null) {
      target.setDocumentFormat(cloneDocumentFormat(source.getDocumentFormat()));
    }
    return target;
  }

  public DocumentBody cloneShortDocumentBody(final DocumentBody source) {
    DocumentBody target = new DocumentBody();
    target.setId(source.getId());
    // target.setBody(source.getBody());
    target.setVersion(source.getVersion());
    if (source.getDocumentFormat() != null) {
      target.setDocumentFormat(cloneDocumentFormat(source.getDocumentFormat()));
    }
    return target;
  }

  public DocumentFormat cloneDocumentFormat(final DocumentFormat source) {
    DocumentFormat target = new DocumentFormat();
    target.setId(source.getId());
    target.setDocumentBusinessType(cloneDocumentBusinessType(source.getDocumentBusinessType()));
    target.setName(source.getName());
    return target;
  }

  public Set<DocumentBusinessType> cloneDocumentBusinessTypes(
      final Set<DocumentBusinessType> source) {
    return source.stream().map(this::cloneDocumentBusinessType).collect(Collectors.toSet());
  }

  public DocumentBusinessType cloneDocumentBusinessType(final DocumentBusinessType source) {
    DocumentBusinessType target = new DocumentBusinessType();
    target.setId(source.getId());
    target.setName(source.getName());
    return target;
  }

}
