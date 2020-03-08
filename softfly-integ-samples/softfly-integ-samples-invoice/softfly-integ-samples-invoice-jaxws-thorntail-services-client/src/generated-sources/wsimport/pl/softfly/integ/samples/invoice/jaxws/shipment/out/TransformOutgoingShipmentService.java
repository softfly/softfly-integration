package pl.softfly.integ.samples.invoice.jaxws.shipment.out;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pl.softfly.integ.doc.entity.DocumentHeader;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.10 Generated source version: 2.1
 */
@WebService(name = "TransformOutgoingShipmentService", targetNamespace = "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/")
@XmlSeeAlso({
    pl.softfly.integ.doc.entity.ObjectFactory.class,
    pl.softfly.integ.endpoint.entity.ObjectFactory.class,
    pl.softfly.integ.entity.ObjectFactory.class,
    pl.softfly.integ.samples.invoice.jaxws.shipment.out.ObjectFactory.class,
    pl.softfly.integ.shipment.entity.ObjectFactory.class
})
public interface TransformOutgoingShipmentService {


  /**
   * @param arg0
   * @return returns java.util.List<pl.softfly.integ.shipment.entity.ShipmentOutgoing>
   */
  @WebMethod
  @WebResult(targetNamespace = "")
  @RequestWrapper(localName = "determine", targetNamespace = "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.shipment.out.Determine")
  @ResponseWrapper(localName = "determineResponse", targetNamespace = "http://out.shipment.jaxws.invoice.samples.integ.softfly.pl/", className = "pl.softfly.integ.samples.invoice.jaxws.shipment.out.DetermineResponse")
  public List<ShipmentOutgoing> determine(
      @WebParam(name = "arg0", targetNamespace = "")
          DocumentHeader arg0);

}
