package tk.hackspace.ui;

import com.vaadin.ui.*;
import javafx.util.Pair;
import tk.hackspace.models.Article;
import tk.hackspace.models.ArticleOrderDetails;

import java.util.ArrayList;


public class ArticleTab extends VerticalLayout {
    private Article article;

    public ArticleTab(Article article) {
        super();
        this.article = article;
        Panel detailPanel = new Panel("Article details");
        //Supplier_aid
        //Article details
        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.setSizeFull();
        ArrayList<Pair<String, String>> articleDetailsFields = new ArrayList<>();
        articleDetailsFields.add(new Pair<>("Supplier aID", article.getSupplierAId()));
        articleDetailsFields.add(new Pair<>("Description", article.getARTICLEDETAILS().getDESCRIPTIONLONG()));
        articleDetailsFields.add(new Pair<>("Manufacturer Name", article.getARTICLEDETAILS().getMANUFACTURERNAME()));
        articleDetailsFields.add(new Pair<>("Manufacturer aID", article.getARTICLEDETAILS().getMANUFACTURERAID()));
        articleDetailsFields.add(new Pair<>("Delivery time", article.getARTICLEDETAILS().getDELIVERYTIME()));

        articleDetailsFields.forEach(pair -> detailsLayout.addComponent(new TextField(pair.getKey(), pair.getValue()) {{
            setReadOnly(true);
            setWidth("100%");
        }}));
        detailPanel.setContent(detailsLayout);
        addComponent(detailPanel);
        //Article features
        Panel featurePanel = new Panel("Articel features");
        VerticalLayout featuresLayout = new VerticalLayout();
        featuresLayout.setSizeFull();
        ArrayList<Pair<String, String>> featuresFields = new ArrayList<>();
        featuresFields.add(new Pair<>("Reference feature system name", article.getARTICLEFEATURES().get(0).getREFERENCEFEATURESYSTEMNAME()));
        featuresFields.add(new Pair<>("Reference feature group id", article.getARTICLEFEATURES().get(0).getReferenceFeatureGroupId()));
        featuresFields.forEach(n -> featuresLayout.addComponent(new TextField(n.getKey(), n.getValue()) {{
            setReadOnly(true);
            setWidth("100%");
        }}));
        featurePanel.setContent(featuresLayout);
        addComponent(featurePanel);

        //article order details
        Panel orderDetailsPanel = new Panel("Order details");
        VerticalLayout orderDetailsLayout = new VerticalLayout();
        orderDetailsLayout.setSizeFull();
        ArrayList<Pair<String, String>> orderDetails = new ArrayList<>();
        ArticleOrderDetails details = article.getARTICLEORDERDETAILS();
        orderDetails.add(new Pair<>("Order unit", details.getORDERUNIT()));
        orderDetails.add(new Pair<>("Price quantity", details.getPRICEQUANTITY()));
        orderDetails.add(new Pair<>("Content unit", details.getCONTENTUNIT()));
        orderDetails.add(new Pair<>("No cu per ou", details.getNOCUPEROU()));
        orderDetails.add(new Pair<>("Minimal quantity", details.getQUANTITYMIN()));
        orderDetails.add(new Pair<>("Quantity interval", details.getQUANTITYINTERVAL()));
        orderDetails.forEach(pair -> orderDetailsLayout.addComponent(new TextField(pair.getKey(), pair.getValue()) {{
            setReadOnly(true);
            setWidth("100%");
        }}));
        orderDetailsPanel.setContent(orderDetailsLayout);
        addComponent(orderDetailsPanel);
        //article price details
        Panel priceDetailsPanel = new Panel("Price details");
        VerticalLayout priceDetailLayout = new VerticalLayout();
        ArrayList<Component> horizontalLayouts = new ArrayList<>();
        HorizontalLayout tempLayout = new HorizontalLayout();
        priceDetailLayout.setSizeFull();

        ArrayList<Pair<String, String>> priceDetailsFields = new ArrayList<>();
        priceDetailsFields.add(new Pair<>("Valid start date", article.getARTICLEPRICEDETAILS().get(0).getDATETIME().get(0).getDate()));
        priceDetailsFields.add(new Pair<>("Valid end date", article.getARTICLEPRICEDETAILS().get(0).getDATETIME().get(1).getDate()));
        priceDetailsFields.forEach(pair -> tempLayout.addComponent(new TextField(pair.getKey(), pair.getValue()) {{
            setReadOnly(true);
        }}));

        priceDetailLayout.addComponent(tempLayout);
        //Prices
        article.getARTICLEPRICEDETAILS().get(0).getARTICLEPRICE().forEach(price -> horizontalLayouts.add(new Panel("Price", new PricePanel(price) {{
            setSizeFull();
        }})));
        horizontalLayouts.forEach(priceDetailLayout::addComponent);
        priceDetailsPanel.setContent(priceDetailLayout);
        addComponent(priceDetailsPanel);

        //mime info (insert picture)
        //TODO insert images.
//        article.getMIMEINFO().getMIME().forEach(mime -> {
//            String basePath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
//            Image image = new Image(mime.getMIMEDESCR(), new FileResource(new File(basePath+"/static/img/"+mime.getMIMESOURCE())));
//            addComponent(image);
//            setComponentAlignment(image,Alignment.TOP_CENTER);
//        });

    }

    public Article getArticle() {
        return article;
    }

}
