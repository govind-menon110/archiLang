
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 Forces Real Elements to have Names.
 *             
 * 
 * <p>Java-Klasse für RealElementType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RealElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.opengroup.org/xsd/archimate/3.0/}ElementType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.opengroup.org/xsd/archimate/3.0/}NameGroup" maxOccurs="unbounded"/>
 *         &lt;group ref="{http://www.opengroup.org/xsd/archimate/3.0/}DocumentationGroup"/>
 *         &lt;group ref="{http://www.opengroup.org/xsd/archimate/3.0/}grp.any"/>
 *         &lt;group ref="{http://www.opengroup.org/xsd/archimate/3.0/}ConceptGroup"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengroup.org/xsd/archimate/3.0/}ConceptAttributeGroup"/>
 *       &lt;attGroup ref="{http://www.opengroup.org/xsd/archimate/3.0/}IdentifierGroup"/>
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RealElementType")
@XmlSeeAlso({
    TechnologyProcess.class,
    Node.class,
    Product.class,
    BusinessObject.class,
    BusinessCollaboration.class,
    Goal.class,
    ImplementationEvent.class,
    Capability.class,
    BusinessEvent.class,
    DistributionNetwork.class,
    ApplicationFunction.class,
    TechnologyInterface.class,
    ApplicationEvent.class,
    Principle.class,
    Requirement.class,
    Contract.class,
    ApplicationService.class,
    Deliverable.class,
    CommunicationNetwork.class,
    TechnologyCollaboration.class,
    ApplicationCollaboration.class,
    Path.class,
    Plateau.class,
    ApplicationComponent.class,
    TechnologyInteraction.class,
    Artifact.class,
    BusinessFunction.class,
    Outcome.class,
    Facility.class,
    Constraint.class,
    BusinessActor.class,
    BusinessInteraction.class,
    WorkPackage.class,
    TechnologyFunction.class,
    Stakeholder.class,
    Device.class,
    Resource.class,
    Representation.class,
    DataObject.class,
    Gap.class,
    Value.class,
    BusinessInterface.class,
    ApplicationProcess.class,
    TechnologyService.class,
    BusinessRole.class,
    Assessment.class,
    SystemSoftware.class,
    Driver.class,
    ApplicationInterface.class,
    TechnologyEvent.class,
    CourseOfAction.class,
    Material.class,
    BusinessService.class,
    Equipment.class,
    ApplicationInteraction.class,
    Meaning.class,
    BusinessProcess.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public abstract class RealElementType
    extends ElementType
{


}
