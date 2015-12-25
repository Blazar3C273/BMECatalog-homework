package tk.hackspace;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.hackspace.dtd.gen.Address;

/**
 * Created by terron on 25.12.15.
 */

public interface AddressRepository extends JpaRepository<Address, Long> {
}
