package tk.hackspace;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by terron on 25.12.15.
 */
@Entity
public class Foo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String fooName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Faa> faasList = new ArrayList<>();

    Foo() {
    }

    public Foo(String fooName, List<Faa> faasList) {
        this.fooName = fooName;
        this.faasList = faasList;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", fooName='" + fooName + '\'' +
                ", faas= '" + faasList + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFooName() {
        return fooName;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    public List<Faa> getFaasList() {
        return faasList;
    }

    public void setFaasList(List<Faa> faasList) {
        this.faasList = faasList;
    }
}
