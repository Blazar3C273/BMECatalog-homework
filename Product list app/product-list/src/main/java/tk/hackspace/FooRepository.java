package tk.hackspace;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by terron on 25.12.15.
 */

public interface FooRepository extends JpaRepository<Foo, Long> {

    Collection<Foo> findByFooName(@Param(value = "st") String st);
}
