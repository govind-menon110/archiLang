
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A base relationship type that can be extended by concrete ArchiMate types.
 * 
 *                 Note that RelationshipType is abstract, so one must have derived types of this type. this is indicated in xml
 *                 by having a tag name of "relationship" and an attribute of xsi:type="AccessRelationship" where AccessRelationship is
 *                 a derived type from RelationshipType.
 *             
 * 
 * <p>Java-Klasse für RelationshipType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RelationshipType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengroup.org/xsd/archimate/3.0/}ConceptType">
 *       &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="target" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipType")
@XmlSeeAlso({
    Access.class,
    Assignment.class,
    Influence.class,
    Aggregation.class,
    Specialization.class,
    Association.class,
    Triggering.class,
    Flow.class,
    Serving.class,
    Realization.class,
    Composition.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public abstract class RelationshipType
    extends ConceptType
{

    @XmlAttribute(name = "source", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Object source;
    @XmlAttribute(name = "target", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Object target;

    /**
     * Ruft den Wert der source-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Object getSource() {
        return source;
    }

    /**
     * Legt den Wert der source-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSource(Object value) {
        this.source = value;
    }

    /**
     * Ruft den Wert der target-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Object getTarget() {
        return target;
    }

    /**
     * Legt den Wert der target-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTarget(Object value) {
        this.target = value;
    }

}
