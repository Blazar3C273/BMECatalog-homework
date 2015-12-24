//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.23 at 08:32:58 PM MSK 
//


package tk.hackspace.dtd.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "classificationgroupid",
        "classificationgroupname",
        "classificationgroupdescr",
        "classificationgroupsynonyms",
        "classificationgroupfeaturetemplates",
        "classificationgroupparentid"
})
@XmlRootElement(name = "CLASSIFICATION_GROUP")
public class ClassificationGroup {

    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "level")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String level;
    @XmlElement(name = "CLASSIFICATION_GROUP_ID", required = true)
    protected String classificationgroupid;
    @XmlElement(name = "CLASSIFICATION_GROUP_NAME", required = true)
    protected String classificationgroupname;
    @XmlElement(name = "CLASSIFICATION_GROUP_DESCR")
    protected String classificationgroupdescr;
    @XmlElement(name = "CLASSIFICATION_GROUP_SYNONYMS")
    protected ClassificationGroupSynonyms classificationgroupsynonyms;
    @XmlElement(name = "CLASSIFICATION_GROUP_FEATURE_TEMPLATES")
    protected ClassificationGroupFeatureTemplates classificationgroupfeaturetemplates;
    @XmlElement(name = "CLASSIFICATION_GROUP_PARENT_ID")
    protected String classificationgroupparentid;

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the level property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the classificationgroupid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCLASSIFICATIONGROUPID() {
        return classificationgroupid;
    }

    /**
     * Sets the value of the classificationgroupid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCLASSIFICATIONGROUPID(String value) {
        this.classificationgroupid = value;
    }

    /**
     * Gets the value of the classificationgroupname property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCLASSIFICATIONGROUPNAME() {
        return classificationgroupname;
    }

    /**
     * Sets the value of the classificationgroupname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCLASSIFICATIONGROUPNAME(String value) {
        this.classificationgroupname = value;
    }

    /**
     * Gets the value of the classificationgroupdescr property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCLASSIFICATIONGROUPDESCR() {
        return classificationgroupdescr;
    }

    /**
     * Sets the value of the classificationgroupdescr property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCLASSIFICATIONGROUPDESCR(String value) {
        this.classificationgroupdescr = value;
    }

    /**
     * Gets the value of the classificationgroupsynonyms property.
     *
     * @return possible object is
     * {@link ClassificationGroupSynonyms }
     */
    public ClassificationGroupSynonyms getCLASSIFICATIONGROUPSYNONYMS() {
        return classificationgroupsynonyms;
    }

    /**
     * Sets the value of the classificationgroupsynonyms property.
     *
     * @param value allowed object is
     *              {@link ClassificationGroupSynonyms }
     */
    public void setCLASSIFICATIONGROUPSYNONYMS(ClassificationGroupSynonyms value) {
        this.classificationgroupsynonyms = value;
    }

    /**
     * Gets the value of the classificationgroupfeaturetemplates property.
     *
     * @return possible object is
     * {@link ClassificationGroupFeatureTemplates }
     */
    public ClassificationGroupFeatureTemplates getCLASSIFICATIONGROUPFEATURETEMPLATES() {
        return classificationgroupfeaturetemplates;
    }

    /**
     * Sets the value of the classificationgroupfeaturetemplates property.
     *
     * @param value allowed object is
     *              {@link ClassificationGroupFeatureTemplates }
     */
    public void setCLASSIFICATIONGROUPFEATURETEMPLATES(ClassificationGroupFeatureTemplates value) {
        this.classificationgroupfeaturetemplates = value;
    }

    /**
     * Gets the value of the classificationgroupparentid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCLASSIFICATIONGROUPPARENTID() {
        return classificationgroupparentid;
    }

    /**
     * Sets the value of the classificationgroupparentid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCLASSIFICATIONGROUPPARENTID(String value) {
        this.classificationgroupparentid = value;
    }

}