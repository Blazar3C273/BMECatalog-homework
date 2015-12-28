package tk.hackspace.jpa.test.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import tk.hackspace.dtd.gen.Article;

/**
 * Created by terron on 28.12.15.
 */
public class ArticleTab {
    public static TabSheet getNewTab(Article article) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label(article.getMode()));
        verticalLayout.addComponent(new Label(String.valueOf(article.getMIMEINFO().getMIME().get(0))));
        verticalLayout.addComponent(new Label(article.getSUPPLIERAID()));
        verticalLayout.addComponent(new Label(String.valueOf(article.getARTICLEPRICEDETAILS().get(0))));
        verticalLayout.setSpacing(true);
        return new TabSheet(verticalLayout);
    }
}
