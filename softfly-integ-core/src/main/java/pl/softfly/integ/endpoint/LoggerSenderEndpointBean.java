package pl.softfly.integ.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.utils.ObjectMapperFactory;


/**
 * Adds logging request/response.
 */
public class LoggerSenderEndpointBean implements SenderEndpoint {

  private static final Logger LOGGER = Logger.getLogger(LoggerSenderEndpointBean.class.getName());

  private SenderEndpoint senderEndpoint;

  private ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  public LoggerSenderEndpointBean(SenderEndpoint senderEndpoint) {
    this.senderEndpoint = senderEndpoint;
  }

  @Override
  public ShipmentOutgoing send(ShipmentOutgoing shipment) {
    try {
      LOGGER.info("Request to " + getClassName() + "\n" + mapper.writeValueAsString(shipment));
      ShipmentOutgoing output = senderEndpoint.send(shipment);
      LOGGER.info("Response from " + getClassName() + "\n" + mapper.writeValueAsString(output));
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClassName() {
    return senderEndpoint.getClass().getName();
  }
}
