package tk.hackspace.ui;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import javafx.util.Pair;
import tk.hackspace.models.ArticlePrice;

import java.util.ArrayList;

/**
 * Created by terron on 30.12.15.
 */
public class PricePanel extends HorizontalLayout {
    public PricePanel(ArticlePrice price) {
        setCaption("Price type is" + price.getPriceType());
        ArrayList<Pair<String, String>> filelds = new ArrayList<>();
        filelds.add(new Pair<>("Price amount", price.getPRICEAMOUNT()));
        filelds.add(new Pair<>("Price currency", price.getPRICECURRENCY()));
        filelds.add(new Pair<>("Tax", price.getTAX()));
        filelds.forEach(pair -> addComponent(new TextField(pair.getKey(), pair.getValue()) {{
            setReadOnly(true);
        }}));
    }
}
