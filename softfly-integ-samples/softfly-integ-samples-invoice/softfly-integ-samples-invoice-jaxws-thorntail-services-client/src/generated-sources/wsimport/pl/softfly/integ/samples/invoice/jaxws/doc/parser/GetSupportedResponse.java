package pl.softfly.integ.samples.invoice.jaxws.doc.parser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.softfly.integ.doc.entity.DocumentFormat;


/**
 * <p>Java class for getSupportedResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getSupportedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://softfly.pl/integ/doc/entity}documentFormat" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSupportedResponse", propOrder = {
    "_return"
})
public class GetSupportedResponse {

  @XmlElement(name = "return")
  protected List<DocumentFormat> _return;

  /**
   * Gets the value of the return property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the return property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getReturn().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocumentFormat }
   */
  public List<DocumentFormat> getReturn() {
    if (_return == null) {
      _return = new ArrayList<DocumentFormat>();
    }
    return this._return;
  }

}
