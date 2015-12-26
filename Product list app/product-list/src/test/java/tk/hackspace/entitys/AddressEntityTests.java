package tk.hackspace.entitys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.hackspace.AddressRepository;
import tk.hackspace.AgreementRepo;
import tk.hackspace.Faa;
import tk.hackspace.ProductListApplication;
import tk.hackspace.dtd.gen.Agreement;
import tk.hackspace.dtd.gen.ObjectFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by terron on 25.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ProductListApplication.class)
public class AddressEntityTests {

    @Autowired
    private AddressRepository fooRepository;
    @Autowired
    private AgreementRepo agreementRepo;
    @Test
    public void testName() throws Exception {
        fooRepository.save(new ObjectFactory().createADDRESS());
        fooRepository.findAll().forEach(System.out::println);
    }

    private List<Faa> getFaa(Random rnd) {
        return
                new ArrayList<Faa>() {{
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                }};
    }

    @Test
    public void testAgreementEntitiesJpaSaveLoad() throws Exception {
        Agreement agreement = new ObjectFactory().createAGREEMENT();
        agreement.setAGREEMENTID("asonueth");
        agreementRepo.save(agreement);
        agreementRepo.findAll().forEach(System.out::println);
    }

    ;
}
