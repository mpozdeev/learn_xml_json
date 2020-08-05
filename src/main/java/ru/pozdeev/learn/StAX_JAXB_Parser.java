package ru.pozdeev.learn;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class StAX_JAXB_Parser {

    public static Shop parseXMLfile(String xmlFilePath) throws JAXBException {
        Shop shop = null;

        List<Category> categoryList = new ArrayList<>();
        Category category = null;

        List<Subcategory> subcategoryList = new ArrayList<>();
        Subcategory subcategory = null;

        List<Product> productList;
        Product product = null;

        String name;

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        JAXBContext jc = JAXBContext.newInstance(Category.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        try {
//            XMLEventReader reader = inputFactory.createXMLEventReader(new FileInputStream(xmlFileName));
            XMLStreamReader reader = inputFactory.createXMLStreamReader(new FileInputStream(xmlFilePath));
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == START_ELEMENT) {
                    switch (reader.getLocalName()) {
                        case "shop":
                            shop = new Shop();
                            break;

                        case "category":
                            category = new Category();
                            name = reader.getAttributeValue(null, "name");
                            if (name != null) {
                                category.setName(name);
                            }
                            break;

                        case "subcategory":
                            subcategory = new Subcategory();
                            name = reader.getAttributeValue(null, "name");
                            if (name != null) {
                                subcategory.setName(name);
                            }
                            break;

                        case "product":
                            while (reader.getEventType() == START_ELEMENT) {
                                JAXBElement<Product> boolElement = unmarshaller.unmarshal(reader, Product.class);
                                product = boolElement.getValue();
                                subcategory.addProduct(product);

//                                if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
//                                    reader.next();
//                                }
                            }
                            break;
                    }
                }

                if (event == END_ELEMENT) {
                    switch (reader.getLocalName()) {
                        case "shop":
                            assert shop != null;
                            shop.setCategoryList(categoryList);
                            break;

                        case "category":
                            categoryList.add(category);
                            break;

                        case "subcategory":
                            assert category != null;
                            category.addSubcategory(subcategory);
                            break;
                    }
                }
            }

            reader.close();

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }

        return shop;
    }
}
