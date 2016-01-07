package tk.hackspace.uploader;

import com.vaadin.ui.Upload;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;


/**
 * Created by terron on 30.12.15.
 */
public class XMLReceivr implements Upload.Receiver {
    Path xmlTmp;

    public Path getXmlTmpPath() {
        return xmlTmp;
    }

    @Override
    public OutputStream receiveUpload(String s, String s1) {
        OutputStream outputStream = null;
        try {
            xmlTmp = Files.createTempFile("xmlTmp" + Calendar.getInstance().getTimeInMillis(), s);

            outputStream = Files.newOutputStream(xmlTmp);

        } catch (IOException e) {
            Logger.getLogger(XMLReceivr.class).error(e);
        }

        return outputStream;
    }
}
