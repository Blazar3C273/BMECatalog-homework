package tk.hackspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by terron on 25.12.15.
 */
@Entity
public class Faa implements Serializable {
    @Override
    public String toString() {
        return "Faa{" +
                "faa_id=" + faa_id +
                ", count=" + count +
                ", TeddyKillerz='" + TeddyKillerz + '\'' +
                '}';
    }

    Faa() {
    }

    public Faa(Integer count, String teddyKillerz) {
        this.count = count;
        TeddyKillerz = teddyKillerz;
    }

    public Long getFaa_id() {
        return faa_id;
    }

    public void setFaa_id(Long faa_id) {
        this.faa_id = faa_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTeddyKillerz() {
        return TeddyKillerz;
    }

    public void setTeddyKillerz(String teddyKillerz) {
        TeddyKillerz = teddyKillerz;
    }

    @Id
    @GeneratedValue
    private Long faa_id;

    private Integer count;
    private String TeddyKillerz;

}
