package pl.softfly.integ.samples.invoice.jaxws.shipment.out;

import java.util.Collection;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.out.TransformOutgoingShipmentBean;

/**
 * http://localhost:8080/TransformOutgoingShipment?wsdl
 */
@WebService(serviceName = "OutgoingShipment")
public class TransformOutgoingShipmentService extends TransformOutgoingShipmentBean {

  @Override
  @WebMethod
  public Collection<ShipmentOutgoing> determine(DocumentHeader documentHeader) {
    return super.determine(documentHeader);
  }

}
