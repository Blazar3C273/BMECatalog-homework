package tk.hackspace.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tk.hackspace.models.BMEcat;

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
BMEcat findById(Long id);
}
