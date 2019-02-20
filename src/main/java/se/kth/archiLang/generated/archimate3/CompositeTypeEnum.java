
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CompositeTypeEnum.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="CompositeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="Grouping"/>
 *     &lt;enumeration value="Location"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CompositeTypeEnum")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum CompositeTypeEnum {

    @XmlEnumValue("Grouping")
    GROUPING("Grouping"),
    @XmlEnumValue("Location")
    LOCATION("Location");
    private final String value;

    CompositeTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CompositeTypeEnum fromValue(String v) {
        for (CompositeTypeEnum c: CompositeTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
