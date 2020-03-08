package pl.softfly.integ.samples.invoice.jaxws.endpoint;

import javax.jws.WebService;
import pl.softfly.integ.endpoint.SenderEndpointBean;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

/**
 * http://localhost:8080/SenderEndpoint?wsdl
 */
@WebService(serviceName = "SenderEndpoint")
public class SenderEndpointService extends SenderEndpointBean {

  @Override
  public ShipmentOutgoing send(ShipmentOutgoing shipment) {
    return super.send(shipment);
  }

}
