package tk.hackspace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import tk.hackspace.dtd.gen.repos.BMECatRepository;
import tk.hackspace.dtd.gen.BMEcat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductListApplication.class)
@WebAppConfiguration
public class ProductListApplicationTests {
    @Autowired
    private BMECatRepository bmeCatRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testParsingXML() throws Exception {
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
}
