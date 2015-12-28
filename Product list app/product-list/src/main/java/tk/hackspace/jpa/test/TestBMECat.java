package tk.hackspace.jpa.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by terron on 26.12.15.
 */
@Entity
public class TestBMECat {
    @Id
    @GeneratedValue
    private Long bmeCatId;

    public static ArrayList<TestBMECat> boilSomeBMECat(int i) {
        ArrayList<TestBMECat> result = new ArrayList<>(i);
        Random rnd = new Random();
        for (int j = 0; j < i; j++) {

            List<TestArticle> testArticles = new ArrayList<TestArticle>() {{
                for (int i = 0; i < 3; i++) {
                    add(new TestArticle("" + rnd.nextInt(), ("" + rnd.nextInt())));
                }
            }};

            TCatalog tCatalog = new TCatalog(testArticles);
            TestHeader headers = new TestHeader("" + rnd.nextInt(), new TestCatalog("" + rnd.nextBoolean(), "catId", "0.1"), new TestSupplier("Supplr name", rnd.nextInt(1000)));
            result.add(new TestBMECat(tCatalog, headers));
        }
        return result;
    }

    @Override
    public String toString() {
        return "TestBMECat{" +
                "bmeCatId=" + bmeCatId +
                ", tCatalog=" + tCatalog +
                ", testHeader=" + testHeader +
                '}';
    }

    public TestBMECat(TCatalog tCatalog, TestHeader testHeader) {
        this.tCatalog = tCatalog;
        this.testHeader = testHeader;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private TCatalog tCatalog;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TestHeader testHeader;

    public TestHeader getTestHeader() {
        return testHeader;
    }

    public void setTestHeader(TestHeader testHeader) {
        this.testHeader = testHeader;
    }

    public TCatalog gettCatalog() {
        return tCatalog;
    }

    public void settCatalog(TCatalog tCatalog) {
        this.tCatalog = tCatalog;
    }


    public TestBMECat() {
    }

    public Long getBmeCatId() {
        return bmeCatId;
    }

    public void setBmeCatId(Long bmeCatId) {
        this.bmeCatId = bmeCatId;
    }
}
