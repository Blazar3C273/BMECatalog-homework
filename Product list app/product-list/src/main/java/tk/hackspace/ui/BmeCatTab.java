package tk.hackspace.ui;

import com.vaadin.ui.*;
import javafx.util.Pair;
import tk.hackspace.models.Address;
import tk.hackspace.models.BMEcat;
import tk.hackspace.models.Catalog;
import tk.hackspace.models.Supplier;

import java.util.*;

/**
 * Created by terron on 28.12.15.
 */
public class BmeCatTab extends VerticalLayout {
    private BMEcat bmEcat;

    public BmeCatTab(BMEcat cat) {
        super();
        this.bmEcat = cat;

        /*BMECat
        name
        version
        catalog_id
        Date+Time
        Currency
        Supplier
            supplier_id
            Supplier_name
            Address
                name
                contact
                Street
                zip
                city
                country
                phone
                fax
                email
                url*/

        Catalog catalog = cat.getHeader().getCatalog();
        //Creating text fields and fill it with BMECat properties
        List<TextField> textFields = Arrays.asList(
                new TextField("Version", cat.getVersion()),
                new TextField("Catalog id", catalog.getCatalogId()),
                new TextField("Generation date", catalog.getDateTime().getDate()),
                new TextField("Currency", catalog.getCurrency())
        );
        //Add fields to form with setting readOnly flags
        textFields.forEach(n -> {
            n.setReadOnly(true);
            n.setWidth("100%");
            addComponent(n);
            setSizeFull();
        });
        //Add price flags
        catalog.getPriceFlag().forEach(priceFlag -> {
            CheckBox c = new CheckBox(priceFlag.getType(), Boolean.parseBoolean(priceFlag.getvalue()));
            c.setReadOnly(true);
            addComponent(c);
        });
        //Creating supplier panel and filling it with content
        Panel supplierPanel = new Panel("Supplier Info");
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setSizeFull();
        supplierPanel.setSizeFull();
        Supplier supplier = cat.getHeader().getSupplier();
        ArrayList<TextField> fields = new ArrayList<>();

        //Building Address layout
        fields.add(new TextField("Supplier name", supplier.getSupplierName()));
        supplier.getSupplierId().forEach(supplierId ->
                fields.add(new TextField("Supplier id (type:" + supplierId.getType() + ")", supplierId.getvalue())));
        fields.forEach(n -> {
            n.setReadOnly(true);
            n.setWidth("100%");
            layout1.addComponent(n);
        });
        //Address panel (double bracelets syntax rullezzz)
        ArrayList<Pair<String, String>> addressFields = new ArrayList<>();

        Address address = supplier.getAddress();
        addressFields.add(new Pair<>("Name", address.getName()));
        if (address.getName2() != null && !address.getName2().isEmpty())
            addressFields.add(new Pair<>("Second Name", address.getName2()));

        addressFields.add(new Pair<>("Country", address.getCOUNTRY()));
        addressFields.add(new Pair<>("Zip", address.getZIP()));
        addressFields.add(new Pair<>("City", address.getCITY()));
        addressFields.add(new Pair<>("Street", address.getSTREET()));
        addressFields.add(new Pair<>("Contact", address.getCONTACT()));
        addressFields.add(new Pair<>("Phone", address.getPHONE()));
        addressFields.add(new Pair<>("Fax", address.getFAX()));
        addressFields.add(new Pair<>("Email", address.getEMAIL()));
        addressFields.add(new Pair<>("Url", address.getURL()));

        layout1.addComponent(new Panel("Address", new VerticalLayout() {{
            addressFields.forEach(pair ->
                    addComponent(new TextField(pair.getKey(), pair.getValue()) {{
                        setReadOnly(true);
                        setWidth("100%");
                    }}));
        }}));

        supplierPanel.setContent(layout1);
        addComponent(supplierPanel);
    }

    public BMEcat getBmEcat() {
        return bmEcat;
    }

}
