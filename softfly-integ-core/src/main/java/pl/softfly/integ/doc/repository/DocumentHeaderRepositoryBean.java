package pl.softfly.integ.doc.repository;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.Shipment;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * Repository.
 */
public class DocumentHeaderRepositoryBean {

  public int findNextDocumentBodyId(DocumentHeader documentHeader) {
    OptionalInt last = documentHeader.getBodies()//
        .stream()//
        .mapToInt(DocumentBody::getId)//
        .max();
    if (last.isPresent()) {
      return last.getAsInt() + 1;//
    } else {
      return 1;
    }
  }

  public DocumentBody findDocumentBodyById(DocumentHeader documentHeader, int id) {
    return documentHeader.getBodies().stream().filter(b -> id == b.getId()).findFirst().get();
  }

  public Integer findCurrentVersion(DocumentHeader documentHeader) {
    OptionalInt current = documentHeader.getBodies()//
        .stream()//
        .mapToInt(DocumentBody::getVersion)//
        .max();
    if (current.isPresent()) {
      return current.getAsInt();//
    } else {
      return null;
    }
  }

  public List<DocumentBody> findCurrent(DocumentHeader documentHeader) {
    OptionalInt current = documentHeader.getBodies()//
        .stream()//
        .mapToInt(DocumentBody::getVersion)//
        .max();
    if (current.isPresent()) {
      return documentHeader.getBodies()//
          .stream()//
          .filter(p -> p.getVersion().equals(current.getAsInt()))//
          .collect(Collectors.toList());
    } else {
      return null;
    }
  }

  public int findNextShipmentId(DocumentHeader documentHeader) {
    if (documentHeader.getShipments() != null) {
      OptionalInt last = documentHeader.getShipments()//
          .stream()//
          .mapToInt(Shipment::getId)//
          .max();
      if (last.isPresent()) {
        return last.getAsInt() + 1;//
      }
    }
    return 1;
  }

  public Stream<ShipmentOutgoing> findOutgoingShipmentStream(DocumentHeader documentHeader) {
    return documentHeader.getShipments().stream().filter(ShipmentOutgoing.class::isInstance)
        .map(ShipmentOutgoing.class::cast);
  }

  public ShipmentOutgoing findOutgoingShipmentById(DocumentHeader documentHeader, int shipmentId) {
    return findOutgoingShipmentStream(documentHeader).filter(s -> shipmentId == s.getId()).findAny()
        .get();
  }
}
