package tk.hackspace;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import tk.hackspace.dtd.gen.BMEcat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@SpringBootApplication
public class ProductListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductListApplication.class, args);
    }


    @Component
    class FooComandLineRunner implements CommandLineRunner {
        private final FooRepository fooRepository;

        @Override
        public void run(String... strings) throws Exception {
            Random rnd = new Random();

            //Arrays.asList("Teddy", "Place 2b", "Bes", "Receptor").forEach(n -> fooRepository.save(new Foo(n, getFaa(rnd))));


            //fooRepository.findAll().forEach(System.out::println);

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
