package pl.softfly.integ.shipment.out;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.doc.entity.ProcessingLog;
import pl.softfly.integ.entity.Participant;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;

/**
 * <ul>
 * <li>1. Check if the shipment has been determined.<br>
 * If not, the shipment require to be determined (TRUE).
 * <li>2. Check if each recipient has a shipment that has been sent.<br>
 * If not, the next shipment require to be determined (TRUE).<br>
 * <li>Otherwise the shipment does not require to be determined (FALSE).
 */
public class IsOutgoingShipmentRequiredBean {

  private static final Logger LOGGER = Logger.getLogger(OutgoingShipment.class.getName());

  public boolean isRequired(DocumentHeader documentHeaderIn) {
    // 1
    if (!existShipmentOutgoing(documentHeaderIn)) {
      createLogRequireToBeDetermined(documentHeaderIn, null);
      return true;
    }

    // 2
    List<Participant> recipientsNotSend = findRecipientsNotSend(documentHeaderIn);
    if (recipientsNotSend.isEmpty()) {
      createLogNotRequireToBeDetermined(documentHeaderIn);
      return false;
    } else {
      createLogRequireToBeDetermined(documentHeaderIn, recipientsNotSend);
      return true;
    }
  }

  /**
   * 1. Check if the shipment has been determined.
   */
  protected boolean existShipmentOutgoing(DocumentHeader documentHeader) {
    if (documentHeader.getShipments() == null) {
      return false;
    }
    return documentHeader.getShipments().stream()
        .anyMatch(shipment -> shipment instanceof ShipmentOutgoing);
  }

  /**
   * 2. Check if each recipient has a shipment that has been sent.
   */
  protected List<Participant> findRecipientsNotSend(DocumentHeader documentHeader) {
    List<Integer> comittedRecipientIds = documentHeader.getShipments().stream()
        .filter(s -> s instanceof ShipmentOutgoing).map(ShipmentOutgoing.class::cast)
        .filter(s -> ShipmentOutgoingStatus.COMITTED.equals(s.getStatus()))
        .map(s -> s.getEndpoint().getParticipant().getId()).distinct().collect(Collectors.toList());

    return documentHeader.getRecipients().stream()
        .filter(r -> !comittedRecipientIds.contains(r.getId())).collect(Collectors.toList());
  }

  protected void createLogRequireToBeDetermined(DocumentHeader documentHeader,
      List<Participant> recipients) {
    String msg = "The shipment outgoing require to be determined.";
    if (recipients != null && !recipients.isEmpty()) {
      StringBuilder sb = new StringBuilder(" [");
      for (Participant p : recipients) {
        sb.append("id=").append(p.getId()).append(", name=").append(p.getName());
      }
      sb.append("]");
      msg += sb.toString();
    }
    LOGGER.info(msg);
    ProcessingLog log = new ProcessingLog();
    log.setSource(OutgoingShipment.class.getSimpleName());
    log.setMsg(msg);
    documentHeader.getProcessingLogs().add(log);
  }

  protected void createLogNotRequireToBeDetermined(DocumentHeader documentHeader) {
    String msg = "The shipment outgoing does not require to be determined.";
    LOGGER.info(msg);
    ProcessingLog log = new ProcessingLog();
    log.setSource(OutgoingShipment.class.getSimpleName());
    log.setMsg(msg);
    documentHeader.getProcessingLogs().add(log);
  }

}
