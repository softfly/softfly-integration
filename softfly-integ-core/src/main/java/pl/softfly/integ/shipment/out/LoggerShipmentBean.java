package pl.softfly.integ.shipment.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerShipmentBean implements OutgoingShipment {

  private static final Logger LOGGER = Logger.getLogger(OutgoingShipment.class.getName());

  private OutgoingShipment outgoingShipment;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerShipmentBean(OutgoingShipment outgoingShipment) {
    this.outgoingShipment = outgoingShipment;
  }

  @Override
  public Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader) {
    try {
      LOGGER
          .info("Request to " + getClassName() + "\n" + mapper.writeValueAsString(documentHeader));
      Collection<ShipmentOutgoing> output = outgoingShipment.determine(documentHeader);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return outgoingShipment.getClass().getName();
  }

}
