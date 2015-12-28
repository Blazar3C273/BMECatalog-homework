package tk.hackspace.jpa.test.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.hackspace.dtd.gen.repos.ArticleRepo;
import tk.hackspace.dtd.gen.repos.BMECatRepository;

import java.util.stream.Collectors;

/**
 * Created by terron on 27.12.15.
 */
@SpringUI(path = "test-ui")
@Theme("valo")
public class TestUI extends UI {
    private final BMECatRepository repo;
    private Accordion bmeCatalogListUI;
    private Button searchButton;
    private TextField filterSupplierTextField;
    private HorizontalLayout mainLayout;
    private Button uploadNewBmeCatButton;
    private Button addNewArticleButton;
    private TextField filterArticleTextField;
    private Panel currentArticlePanel;
    private Accordion articleListUI;
    private Button filterArticleButton;
    private ArticleRepo articleRepo;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        initComponets();
        buildLayout();


    }

    @Autowired
    public TestUI(BMECatRepository bmeCatRepo) {
        this.repo = bmeCatRepo;
    }

    private void updateCatalogList() {
        if (bmeCatalogListUI != null) {
            int selectedIndex = bmeCatalogListUI.getTabIndex();
            bmeCatalogListUI.removeAllComponents();
            repo.findAll().forEach(cat -> bmeCatalogListUI.addTab(BMECatAccordionTabFactory.boilTab(cat), cat.getHEADER().getSUPPLIER().getSUPPLIERNAME()));

            bmeCatalogListUI.setSelectedTab(selectedIndex);
        }

    }

    private void buildLayout() {
        setSizeFull();
        mainLayout.setSizeFull();
        VerticalLayout leftColumn = new VerticalLayout();
        HorizontalLayout filterBar = new HorizontalLayout();
        filterSupplierTextField.setWidth("100%");
        searchButton.setWidth("100%");
        bmeCatalogListUI.setWidth(100f, Unit.PERCENTAGE);
        bmeCatalogListUI.setHeight("100%");
        filterArticleTextField.setWidth("100%");
        filterArticleButton.setWidth("100%");


        filterBar.addComponents(filterSupplierTextField, searchButton);
        filterBar.setComponentAlignment(filterSupplierTextField, Alignment.TOP_LEFT);
        filterBar.setComponentAlignment(searchButton, Alignment.TOP_RIGHT);
        filterBar.setExpandRatio(filterSupplierTextField, 3f);
        filterBar.setExpandRatio(searchButton, 1f);
        filterBar.setWidth(100f, Unit.PERCENTAGE);

        Panel testPanel = new Panel();
        testPanel.setHeight(100f, Unit.PERCENTAGE);
        bmeCatalogListUI.setHeightUndefined();
        testPanel.setContent(bmeCatalogListUI);

        leftColumn.addComponents(filterBar, testPanel, uploadNewBmeCatButton);
        leftColumn.setExpandRatio(filterBar, 10);
        leftColumn.setExpandRatio(uploadNewBmeCatButton, 10);
        leftColumn.setExpandRatio(testPanel, 100);

        leftColumn.setComponentAlignment(filterBar, Alignment.TOP_CENTER);
        leftColumn.setComponentAlignment(testPanel, Alignment.TOP_CENTER);
        leftColumn.setComponentAlignment(uploadNewBmeCatButton, Alignment.BOTTOM_CENTER);

        VerticalLayout centerColumn = new VerticalLayout();
        HorizontalLayout filterArticleBar = new HorizontalLayout();

        filterArticleBar.addComponents(filterArticleTextField, filterArticleButton);
        filterArticleBar.setComponentAlignment(filterArticleTextField, Alignment.TOP_LEFT);
        filterArticleBar.setComponentAlignment(filterArticleButton, Alignment.TOP_RIGHT);
        filterArticleBar.setExpandRatio(filterArticleTextField, 3f);
        filterArticleBar.setExpandRatio(filterArticleButton, 1f);
        filterArticleBar.setWidth("100%");

        Panel articlePanel = new Panel();
        articlePanel.setHeight("100%");

        articleListUI.setHeightUndefined();
        articlePanel.setContent(articleListUI);

        centerColumn.addComponents(filterArticleBar, articlePanel, addNewArticleButton);
        centerColumn.setExpandRatio(filterArticleBar, 1);
        centerColumn.setExpandRatio(addNewArticleButton, 1);
        centerColumn.setExpandRatio(articlePanel, 10);
        centerColumn.setComponentAlignment(filterArticleBar, Alignment.TOP_CENTER);
        centerColumn.setComponentAlignment(articlePanel, Alignment.TOP_CENTER);
        centerColumn.setComponentAlignment(addNewArticleButton, Alignment.BOTTOM_CENTER);


        leftColumn.setHeight("100%");
        centerColumn.setHeight("100%");
        currentArticlePanel.setHeight("100%");

        mainLayout.addComponents(leftColumn, centerColumn, currentArticlePanel);

        mainLayout.setComponentAlignment(leftColumn, Alignment.TOP_LEFT);
        mainLayout.setComponentAlignment(centerColumn, Alignment.TOP_LEFT);
        mainLayout.setComponentAlignment(currentArticlePanel, Alignment.TOP_LEFT);
        mainLayout.setExpandRatio(leftColumn, 1);
        mainLayout.setExpandRatio(centerColumn, 1);
        mainLayout.setExpandRatio(currentArticlePanel, 2);

        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

    private void initComponets() {
        mainLayout = new HorizontalLayout();
        bmeCatalogListUI = new Accordion();
        searchButton = new Button("Filter");
        filterArticleTextField = new TextField();
        filterSupplierTextField = new TextField();
        searchButton.addClickListener(clickEvent1 -> {
            bmeCatalogListUI.removeAllComponents();
            repo.findAll().stream().filter(bmEcat ->
                    bmEcat.getHEADER().getSUPPLIER().getSUPPLIERNAME().equalsIgnoreCase(filterSupplierTextField.getValue()))
                    .forEach(n -> bmeCatalogListUI.addTab(BMECatAccordionTabFactory.boilTab(n), n.getHEADER().getSUPPLIER().getSUPPLIERNAME()));
        });
        filterSupplierTextField.setInputPrompt("Filter by Supplier Name");
        addNewArticleButton = new Button("Add new Article");
        //TODO add onClickListener
        uploadNewBmeCatButton = new Button("Upload new catalog");
        //TODO add onClickListener
        uploadNewBmeCatButton.addClickListener(n -> {
            //repo.save(TestBMECat.boilSomeBMECat(1));
            updateCatalogList();
        });


        articleListUI = new Accordion();
        filterArticleTextField = new TextField();
        filterArticleTextField.setInputPrompt("Filter by ");
        filterArticleButton = new Button("Filter");
        //TODO add onClickListener
        currentArticlePanel = new Panel();
        Logger logger = Logger.getLogger(TestUI.class);
        currentArticlePanel.addClickListener(clickEvent -> logger.info("Clicked"));
        repo.flush();
        bmeCatalogListUI.addSelectedTabChangeListener(listener -> {
            articleListUI.removeAllComponents();
            int index = bmeCatalogListUI.getTabPosition(bmeCatalogListUI.getTab(bmeCatalogListUI.getSelectedTab()));
            repo.findAll().get(index).getTNEWCATALOG().getArticle().forEach(article ->
                    articleListUI.addTab(ArticleTab.getNewTab(article), article.getARTICLEDETAILS().getDESCRIPTIONSHORT()));
        });
        repo.findAll().forEach(testBMECat ->
                bmeCatalogListUI.addTab(BMECatAccordionTabFactory.boilTab(testBMECat), testBMECat.getHEADER().getSUPPLIER().getSUPPLIERNAME()));


    }

    public void setArticleRepo(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }
}
