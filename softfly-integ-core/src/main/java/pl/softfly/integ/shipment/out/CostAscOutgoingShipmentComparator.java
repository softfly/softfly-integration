package pl.softfly.integ.shipment.out;

import java.util.Comparator;
import java.util.Objects;
import pl.softfly.integ.shipment.entity.ShipmentOutgoing;

/**
 * The cost of preparation for sending. Compare which endpoint is better to choose.
 *
 * @author Grzegorz Ziemski
 */
public class CostAscOutgoingShipmentComparator implements Comparator<ShipmentOutgoing> {

  @Override
  public int compare(ShipmentOutgoing o1, ShipmentOutgoing o2) {
    Integer c1 = Objects.nonNull(o1.getCost()) ? o1.getCost() : 0;
    Integer c2 = Objects.nonNull(o2.getCost()) ? o2.getCost() : 0;
    return Integer.compare(c1, c2);
  }

}
