package tk.hackspace.jpa.test;

import tk.hackspace.dtd.gen.ArticleDetails;

import javax.persistence.*;

/**
 * Created by terron on 26.12.15.
 */
@Entity
@Table(name = "T_ARTICLE")
public class TestArticle {
    @Id
    @GeneratedValue
    private Long articleId;

    public TestArticle(String mode, String supplierAId) {
        this.mode = mode;
        this.supplierAId = supplierAId;
    }

    private String mode;
    private String supplierAId;

    //private ArticleDetails articleDetails;

    public TestArticle() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSupplierAId() {
        return supplierAId;
    }

    public void setSupplierAId(String supplierAId) {
        this.supplierAId = supplierAId;
    }

    @Override
    public String toString() {
        return "TestArticle{" +
                "articleId=" + articleId +
                ", mode='" + mode + '\'' +
                ", supplierAId='" + supplierAId + '\'' +
                '}';
    }
}
