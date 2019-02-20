
package se.kth.archiLang.generated.archimate3;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *                 A base string type for multi-language strings.
 *             
 * 
 * <p>Java-Klasse für LangStringType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="LangStringType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LangStringType", propOrder = {
    "value"
})
@XmlSeeAlso({
    PreservedLangStringType.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class LangStringType {

    @XmlValue
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String value;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String lang;

    /**
     * Ruft den Wert der value-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getValue() {
        return value;
    }

    /**
     * Legt den Wert der value-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Ruft den Wert der lang-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getLang() {
        return lang;
    }

    /**
     * Legt den Wert der lang-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2019-02-20T07:24:48+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLang(String value) {
        this.lang = value;
    }

}
