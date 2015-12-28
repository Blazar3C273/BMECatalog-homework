package tk.hackspace.jpa.test;

import javax.persistence.*;

/**
 * Created by terron on 26.12.15.
 */
@Entity
@Table(name = "T_SUPPLIER")
public class TestSupplier {
    @Id
    @GeneratedValue
    @Column(name = "SUPPLIER_ID")
    private Long supplierId;
    private String name;
    private Integer zip;

    public String getName() {
        return name;
    }

    public TestSupplier() {
    }

    public TestSupplier(String name, Integer zip) {
        this.name = name;
        this.zip = zip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
