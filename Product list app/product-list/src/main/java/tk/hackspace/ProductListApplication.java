package tk.hackspace;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import tk.hackspace.dtd.gen.repos.BMECatRepository;
import tk.hackspace.dtd.gen.BMEcat;
import tk.hackspace.jpa.test.BMECatRepo;
import tk.hackspace.jpa.test.TestBMECat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ProductListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductListApplication.class, args);
    }


    @Component
    class FooComandLineRunner implements CommandLineRunner {
        private final FooRepository fooRepository;
        @Autowired
        private BMECatRepository bmeCatRepository;
        @Autowired
        private BMECatRepo repo;

        @Override
        public void run(String... strings) throws Exception {
            repo.save(TestBMECat.boilSomeBMECat(50));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            JAXBContext jaxbContext = JAXBContext.newInstance(BMEcat.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            UnmarshallerHandler unmarshallerHandler = jaxbUnmarshaller.getUnmarshallerHandler();
            xr.setContentHandler(unmarshallerHandler);
            FileInputStream xmlStream = new FileInputStream(new File("/mnt/oldE/Projects/German Test Project/Product list app/product-list/src/main/resources/static/homeWork.xml"));
            InputSource xmlSource = new InputSource(xmlStream);
            xr.parse(xmlSource);

            BMEcat bmEcat = (BMEcat) unmarshallerHandler.getResult();
            bmeCatRepository.save(bmEcat);


            System.out.println("bmEcat = " + bmEcat);
        }

        private List<Faa> getFaa(Random rnd) {

            return
                    new ArrayList<Faa>() {{
                        add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                        add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                        add(new Faa(rnd.nextInt(), "" + rnd.nextInt()));
                    }};
        }

        @Autowired
        public FooComandLineRunner(FooRepository fooRepository) {
            this.fooRepository = fooRepository;
        }
    }


}
