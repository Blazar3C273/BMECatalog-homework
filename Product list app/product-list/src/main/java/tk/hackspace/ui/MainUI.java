package tk.hackspace.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.SystemError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import tk.hackspace.models.Article;
import tk.hackspace.models.BMEcat;
import tk.hackspace.models.repos.BMECatRepository;
import tk.hackspace.uploader.XMLReceivr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;


@SpringUI(path = "/")
@Theme("tests-valo-dark")
public class MainUI extends UI {
    private final BMECatRepository repo;
    private Accordion bmeCatalogListUI;
    private Button searchButton;
    private TextField filterSupplierTextField;
    private HorizontalLayout mainLayout;
    private Button addNewArticleButton;
    private TextField filterArticleTextField;
    private Panel currentArticlePanel;
    private Accordion articleListUI;
    private Button filterArticleButton;
    private static final Logger logger = Logger.getLogger(MainUI.class);
    private Upload upload;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initComponets();
        buildLayout();
    }

    @Autowired
    public MainUI(BMECatRepository bmeCatRepo) {
        this.repo = bmeCatRepo;
    }

    private void updateCatalogList() {
        if (bmeCatalogListUI != null) {
            bmeCatalogListUI.removeAllComponents();
            repo.findAll().forEach(cat -> bmeCatalogListUI.addTab(new BmeCatTab(cat), cat.getHeader().getSupplier().getSupplierName()));
            bmeCatalogListUI.setSelectedTab(0);
        }

    }

    private void parceXML(XMLReceivr receiver) throws SAXException, ParserConfigurationException, JAXBException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();
        JAXBContext jaxbContext = JAXBContext.newInstance(BMEcat.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        UnmarshallerHandler unmarshallerHandler = jaxbUnmarshaller.getUnmarshallerHandler();
        xr.setContentHandler(unmarshallerHandler);
        InputSource xmlSource;
        BMEcat bmEcat;
        try (FileInputStream xmlStream = new FileInputStream(receiver.getXmlTmpPath().toFile())) {
            xmlSource = new InputSource(xmlStream);
            xr.parse(xmlSource);
            bmEcat = (BMEcat) unmarshallerHandler.getResult();
            repo.save(bmEcat);
            repo.flush();
            xmlStream.close();
            updateCatalogList();
        } catch (IOException e) {
            logger.error(e);
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
        filterArticleButton.setEnabled(false);
        filterArticleTextField.setEnabled(false);


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

        leftColumn.addComponents(filterBar, testPanel, upload);
        leftColumn.setExpandRatio(filterBar, 10);
        leftColumn.setExpandRatio(upload, 10);
        leftColumn.setExpandRatio(testPanel, 100);

        leftColumn.setComponentAlignment(filterBar, Alignment.TOP_CENTER);
        leftColumn.setComponentAlignment(testPanel, Alignment.TOP_CENTER);
        leftColumn.setComponentAlignment(upload, Alignment.BOTTOM_CENTER);

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

        Button.ClickListener clickListener = clickEvent1 -> {
            if (filterSupplierTextField.getValue().isEmpty() && bmeCatalogListUI.getComponentCount() > 0)
                return;
            else if (bmeCatalogListUI.getComponentCount() == 0 && filterSupplierTextField.getValue().isEmpty()) {
                updateCatalogList();
                return;
            }

            bmeCatalogListUI.removeAllComponents();
            articleListUI.removeAllComponents();
            currentArticlePanel.setVisible(false);

            repo.findAll().stream().filter(bmEcat ->
                    bmEcat.getHeader().getSupplier().getSupplierName().equalsIgnoreCase(filterSupplierTextField.getValue()))
                    .forEach(n -> bmeCatalogListUI.addTab(new BmeCatTab(n), n.getHeader().getSupplier().getSupplierName()));
        };
        searchButton.addClickListener(clickListener);
        filterSupplierTextField.setInputPrompt("Filter by Supplier Name");
        addNewArticleButton = new Button("Add new Article");
        addNewArticleButton.setEnabled(false);
        //TODO add onClickListener


        articleListUI = new Accordion();
        filterArticleTextField = new TextField();
        filterArticleTextField.setInputPrompt("Filter by ");
        filterArticleButton = new Button("Filter");
        //TODO add onClickListener
        currentArticlePanel = new Panel();
        //Choosing catalog
        bmeCatalogListUI.addSelectedTabChangeListener(listener -> {
            articleListUI.removeAllComponents();
            List<Article> articleList =
                    ((BmeCatTab) listener.getTabSheet().getSelectedTab()).getBmEcat().getTNEWCATALOG().getArticle();

            articleList.forEach(article ->
                    articleListUI.addTab(new BreafArticleTab(article), article.getARTICLEDETAILS().getDESCRIPTIONSHORT()));
        });

        articleListUI.addSelectedTabChangeListener(event -> {
            Article article = ((BreafArticleTab) event.getTabSheet().getSelectedTab()).getArticle();
            currentArticlePanel.setContent(new ArticleTab(article));
            currentArticlePanel.setVisible(true);
        });

        repo.findAll().forEach(testBMECat ->
                bmeCatalogListUI.addTab(new BmeCatTab(testBMECat), testBMECat.getHeader().getSupplier().getSupplierName()));

        upload = new Upload(null, new XMLReceivr());
        upload.setImmediate(false);

        upload.setButtonCaption("Upload File");

        UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(upload);
        uploadInfoWindow.setPosition((int) getWidth() / 2, (int) getHeight() / 2);
        upload.addStartedListener((Upload.StartedListener) event -> {
            if (uploadInfoWindow.getParent() == null) {
                UI.getCurrent().addWindow(uploadInfoWindow);
            }
            uploadInfoWindow.setClosable(false);
        });
        upload.addFinishedListener((Upload.FinishedListener) event -> {
            {
                uploadInfoWindow.setClosable(true);
                try {
                    parceXML((XMLReceivr) event.getUpload().getReceiver());
                } catch (SAXException | ParserConfigurationException | JAXBException e) {
                    logger.error(e);
                    upload.setComponentError(new SystemError("Uploading or parsing error.", e));
                }
            }
        });


    }
}
