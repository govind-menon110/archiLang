
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AccessTypeEnum.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AccessTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="Access"/>
 *     &lt;enumeration value="Read"/>
 *     &lt;enumeration value="Write"/>
 *     &lt;enumeration value="ReadWrite"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccessTypeEnum")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum AccessTypeEnum {

    @XmlEnumValue("Access")
    ACCESS("Access"),
    @XmlEnumValue("Read")
    READ("Read"),
    @XmlEnumValue("Write")
    WRITE("Write"),
    @XmlEnumValue("ReadWrite")
    READ_WRITE("ReadWrite");
    private final String value;

    AccessTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccessTypeEnum fromValue(String v) {
        for (AccessTypeEnum c: AccessTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
