
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A base element type that can be extended by concrete ArchiMate types.
 * 
 *                 Note that ElementType is abstract, so one must have derived types of this type. this is indicated in xml
 *                 by having a tag name of "element" and an attribute of xsi:type="BusinessRole" where BusinessRole is
 *                 a derived type from ElementType.
 *             
 * 
 * <p>Java-Klasse für ElementType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ElementType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengroup.org/xsd/archimate/3.0/}ConceptType">
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementType")
@XmlSeeAlso({
    RelationshipConnectorType.class,
    RealElementType.class,
    CompositeType.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public abstract class ElementType
    extends ConceptType
{


}
