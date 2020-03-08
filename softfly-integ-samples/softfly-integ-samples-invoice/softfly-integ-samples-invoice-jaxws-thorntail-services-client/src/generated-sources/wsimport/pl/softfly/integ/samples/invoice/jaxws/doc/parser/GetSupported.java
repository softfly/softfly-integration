package pl.softfly.integ.samples.invoice.jaxws.doc.parser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentBusinessType;


/**
 * <p>Java class for getSupported complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getSupported">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://softfly.pl/integ/doc/entity}documentBusinessType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSupported", propOrder = {
    "arg0"
})
public class GetSupported {

  protected List<DocumentBusinessType> arg0;

  /**
   * Gets the value of the arg0 property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the arg0 property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getArg0().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocumentBusinessType }
   */
  public List<DocumentBusinessType> getArg0() {
    if (arg0 == null) {
      arg0 = new ArrayList<DocumentBusinessType>();
    }
    return this.arg0;
  }

}
