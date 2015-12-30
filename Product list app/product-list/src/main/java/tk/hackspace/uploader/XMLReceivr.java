package tk.hackspace.uploader;

import com.vaadin.ui.Upload;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;


/**
 * Created by terron on 30.12.15.
 */
public class XMLReceivr implements Upload.Receiver {
    public Path getXmlTmpPath() {
        return xmlTmp;
    }

    Path xmlTmp;

    @Override
    public OutputStream receiveUpload(String s, String s1) {
        OutputStream outputStream = null;

        Random random = new Random();
        try {
            xmlTmp = Files.createTempFile("xmlTmp" + random.nextInt(), s);
            outputStream = Files.newOutputStream(xmlTmp);

        } catch (IOException e) {
            Logger.getLogger(XMLReceivr.class).error(e);
        }

        return outputStream;
    }
}
