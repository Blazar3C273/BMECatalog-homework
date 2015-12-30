package tk.hackspace.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import tk.hackspace.models.BMEcat;

import java.util.Collection;

/**
 * Created by terron on 27.12.15.
 */
@RepositoryRestResource
public interface BMECatRepository extends JpaRepository<BMEcat, Long> {
//  TODO rest service will be here
//    String query= "";
//    @RestResource(path = "by-supplier-name")
//    @Query(query)
//    public Collection<BMEcat> findBySupplierName(@Param("supplier_name") String supplier_name);
//
}
