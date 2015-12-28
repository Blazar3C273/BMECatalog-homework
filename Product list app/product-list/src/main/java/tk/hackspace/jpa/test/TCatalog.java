package tk.hackspace.jpa.test;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_TCATALOG")
public class TCatalog {
    @Id
    @GeneratedValue
    @Column(name = "TCATALOG_ID")
    private Long id;

    public TCatalog(List<TestArticle> testArticles) {
        this.testArticles = testArticles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestArticle> testArticles;

    public TCatalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TestArticle> getTestArticles() {
        return testArticles;
    }

    @Override
    public String toString() {
        return "TCatalog{" +
                "id=" + id +
                ", testArticles=" + testArticles +
                '}';
    }

    public void setTestArticles(List<TestArticle> testArticles) {
        this.testArticles = testArticles;
    }
}
