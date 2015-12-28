package tk.hackspace.jpa.test.ui;

import com.vaadin.ui.*;
import tk.hackspace.dtd.gen.BMEcat;
import tk.hackspace.jpa.test.TestBMECat;

/**
 * Created by terron on 28.12.15.
 */
public class BMECatAccordionTabFactory {
    public static Component boilTab(BMEcat cat) {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label(cat.getHEADER().getCATALOG().getCATALOGNAME()));
        layout.addComponent(new Label(cat.getHEADER().getCATALOG().getLANGUAGE()));
        layout.setSpacing(true);
        return layout;
    }
}
