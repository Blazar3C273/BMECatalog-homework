//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.23 at 08:32:58 PM MSK 
//


package tk.hackspace.models;


import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "article"
})
@XmlRootElement(name = "T_NEW_CATALOG")
@Entity
public class TNewCatalog {
    @XmlElement(name = "ARTICLE")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    protected List<Article> article;
    @XmlTransient
    @Transient
    private HashMap<Long, Article> articleMap;
    @Id
    @GeneratedValue
    @XmlTransient
    private Long id;

    public TNewCatalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of the article property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the article property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticle().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Article }
     */
    public List<Article> getArticle() {
        if (article == null) {
            article = new ArrayList<>();
        }
        return this.article;
    }

    public void setArticle(List<Article> article) {
        Logger.getLogger(TNewCatalog.class).info("set Article is invoked.");
        this.article = article;

    }

    public HashMap<Long, Article> getArticlesMap() {
        if (articleMap == null) {
            articleMap = new HashMap<Long, Article>() {{
                getArticle().forEach(article1 -> this.put(article1.getArticle_id(), article1));
            }};
        }
        return articleMap;
    }
    @Override
    public String toString() {
        return "TNewCatalog{" +
                ", article=" + article +
                ", id=" + id +
                '}';
    }
}
