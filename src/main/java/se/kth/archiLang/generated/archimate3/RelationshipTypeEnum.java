
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RelationshipTypeEnum.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="RelationshipTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="Composition"/>
 *     &lt;enumeration value="Aggregation"/>
 *     &lt;enumeration value="Assignment"/>
 *     &lt;enumeration value="Realization"/>
 *     &lt;enumeration value="Serving"/>
 *     &lt;enumeration value="Access"/>
 *     &lt;enumeration value="Influence"/>
 *     &lt;enumeration value="Triggering"/>
 *     &lt;enumeration value="Flow"/>
 *     &lt;enumeration value="Specialization"/>
 *     &lt;enumeration value="Association"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RelationshipTypeEnum")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum RelationshipTypeEnum {

    @XmlEnumValue("Composition")
    COMPOSITION("Composition"),
    @XmlEnumValue("Aggregation")
    AGGREGATION("Aggregation"),
    @XmlEnumValue("Assignment")
    ASSIGNMENT("Assignment"),
    @XmlEnumValue("Realization")
    REALIZATION("Realization"),
    @XmlEnumValue("Serving")
    SERVING("Serving"),
    @XmlEnumValue("Access")
    ACCESS("Access"),
    @XmlEnumValue("Influence")
    INFLUENCE("Influence"),
    @XmlEnumValue("Triggering")
    TRIGGERING("Triggering"),
    @XmlEnumValue("Flow")
    FLOW("Flow"),
    @XmlEnumValue("Specialization")
    SPECIALIZATION("Specialization"),
    @XmlEnumValue("Association")
    ASSOCIATION("Association");
    private final String value;

    RelationshipTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RelationshipTypeEnum fromValue(String v) {
        for (RelationshipTypeEnum c: RelationshipTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
