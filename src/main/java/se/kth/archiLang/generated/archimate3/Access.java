
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Access complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Access">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengroup.org/xsd/archimate/3.0/}RelationshipType">
 *       &lt;attribute name="accessType" type="{http://www.opengroup.org/xsd/archimate/3.0/}AccessTypeEnum" default="Access" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Access")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Access
    extends RelationshipType
{

    @XmlAttribute(name = "accessType")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AccessTypeEnum accessType;

    /**
     * Ruft den Wert der accessType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AccessTypeEnum }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AccessTypeEnum getAccessType() {
        if (accessType == null) {
            return AccessTypeEnum.ACCESS;
        } else {
            return accessType;
        }
    }

    /**
     * Legt den Wert der accessType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessTypeEnum }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAccessType(AccessTypeEnum value) {
        this.accessType = value;
    }

}
