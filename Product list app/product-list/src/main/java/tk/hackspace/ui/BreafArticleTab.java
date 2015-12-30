package tk.hackspace.ui;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import tk.hackspace.models.Article;


public class BreafArticleTab extends VerticalLayout {
    private Article article;

    public BreafArticleTab(Article article) {
        super();
        this.article = article;
        addComponent(new TextArea("", article.getARTICLEDETAILS().getDESCRIPTIONLONG()) {{
            setReadOnly(true);
            setWidth("100%");
        }});
    }

    public Article getArticle() {
        return article;
    }
}
