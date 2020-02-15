package pl.softfly.integ.endpoint;

import java.util.Objects;
import java.util.logging.Logger;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.repository.DocumentFormatRepositoryBean;
import pl.softfly.integ.endpoint.entity.Endpoint;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;
import pl.softfly.integ.shipment.entity.ShipmentOutgoingStatus;


/**
 * Send the document via endpoint.
 */
public class SenderEndpointBean implements SenderEndpoint {

  private static final Logger LOGGER = Logger.getLogger(SenderEndpointBean.class.getName());

  protected DocumentFormatRepositoryBean documentFormatRepository =
      new DocumentFormatRepositoryBean();

  /**
   * {@inheritDoc}
   */
  @Override
  public ShipmentOutgoing send(ShipmentOutgoing shipment) {
    Objects.requireNonNull(shipment);
    DocumentBody documentBody = shipment.getDocumentBody();

    if (send(documentBody, shipment.getEndpoint())) {
      shipment.setStatus(ShipmentOutgoingStatus.COMITTED);
    } else {
      shipment.setStatus(ShipmentOutgoingStatus.ERROR);
    }
    return shipment;
  }

  protected boolean send(DocumentBody documentBody, Endpoint endpoint) {
    Objects.requireNonNull(documentBody);
    Objects.requireNonNull(endpoint);
    if (endpoint.getDocumentFormat().equals(getDocumentFormatRepository().newInvoice3())) {
      LOGGER.info("Sent documentBody to endpoint");
      return true;
    } else {
      LOGGER.info("Not sent documentBody to endpoint");
      return false;
    }
  }

  public DocumentFormatRepositoryBean getDocumentFormatRepository() {
    return documentFormatRepository;
  }

  public void setDocumentFormatRepository(DocumentFormatRepositoryBean documentFormatRepository) {
    this.documentFormatRepository = documentFormatRepository;
  }
}
