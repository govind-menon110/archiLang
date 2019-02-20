
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r DataType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="boolean"/>
 *     &lt;enumeration value="currency"/>
 *     &lt;enumeration value="date"/>
 *     &lt;enumeration value="time"/>
 *     &lt;enumeration value="number"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataType")
@XmlEnum
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public enum DataType {

    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("boolean")
    BOOLEAN("boolean"),
    @XmlEnumValue("currency")
    CURRENCY("currency"),
    @XmlEnumValue("date")
    DATE("date"),
    @XmlEnumValue("time")
    TIME("time"),
    @XmlEnumValue("number")
    NUMBER("number");
    private final String value;

    DataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataType fromValue(String v) {
        for (DataType c: DataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
