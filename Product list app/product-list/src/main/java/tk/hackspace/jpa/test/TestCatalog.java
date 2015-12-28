package tk.hackspace.jpa.test;

import javax.persistence.*;


@Entity
@Table(name = "T_CATALOG")
public class TestCatalog {
    @Id
    @Column(name = "CATOLOG_ID")
    @GeneratedValue
    private Long catalogId;

    public TestCatalog(String language, String catalogSystemId, String catalogVersion) {
        this.language = language;
        this.catalogSystemId = catalogSystemId;
        this.catalogVersion = catalogVersion;
    }

    private String language;
    private String catalogSystemId;
    private String catalogVersion;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCatologSystemId() {
        return catalogSystemId;
    }

    public void setCatologSystemId(String catologSystemId) {
        this.catalogSystemId = catologSystemId;
    }

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }

    TestCatalog() {

    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public String toString() {
        return "TestCatalog{" +
                "catalogId=" + catalogId +
                ", language='" + language + '\'' +
                ", catalogSystemId='" + catalogSystemId + '\'' +
                ", catalogVersion='" + catalogVersion + '\'' +
                '}';
    }
}
