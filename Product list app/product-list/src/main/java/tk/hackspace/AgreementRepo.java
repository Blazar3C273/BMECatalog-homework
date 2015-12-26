package tk.hackspace;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.hackspace.dtd.gen.Agreement;

/**
 * Created by terron on 25.12.15.
 */
public interface AgreementRepo extends JpaRepository<Agreement, String> {
}
