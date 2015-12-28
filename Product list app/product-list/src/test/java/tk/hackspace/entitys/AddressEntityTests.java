package tk.hackspace.entitys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.hackspace.AddressRepository;

import tk.hackspace.Faa;
import tk.hackspace.ProductListApplication;
import tk.hackspace.dtd.gen.repos.BMECatRepository;
import tk.hackspace.dtd.gen.ObjectFactory;
import tk.hackspace.jpa.test.*;

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
    private BMECatRepo bmeCatRepo;
    @Autowired
    private BMECatRepository bmeCatRepository;
    @Test
    public void testName() throws Exception {
        fooRepository.save(new ObjectFactory().createADDRESS());
        fooRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testRealBMECatJPA() throws Exception {


    }

    @Test
    public void testJPA() throws Exception {
        ArrayList<TestBMECat> testBmeCats = TestBMECat.boilSomeBMECat(2);
        bmeCatRepo.save(testBmeCats);
        bmeCatRepo.findAll().forEach(System.out::println);
        TestHeader testHeader = testBmeCats.get(0).getTestHeader();
        testHeader.setGeneratorinfo("Scrillex");
        testBmeCats.get(0).setTestHeader(testHeader);
        bmeCatRepo.save(testBmeCats.get(0));
        bmeCatRepo.findAll().forEach(System.out::println);

    }

    private List<Faa> getFaa(Random rnd) {
        return
                new ArrayList<Faa>() {{
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                    add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                }};
    }
}
