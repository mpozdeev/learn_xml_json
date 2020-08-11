package ru.pozdeev.learn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class MyParser {

    //Use StAX, JAXB
    public static Shop getObjectFromXml(String xmlFilePath) throws JAXBException {
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

    public static String getXmlFromObj(Shop shop) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(shop, new File(filePath)); // маршаллинг объекта в файл
            marshaller.marshal(shop, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static String getXmlFromJson(String jsonFilePath) {
        return getXmlFromObj(MyParser.getObjectFromJson(jsonFilePath));
    }

    //Use GSON
    public static Shop getObjectFromJson(String jsonFilePath) {
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader(jsonFilePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e);
        }
        Gson gson = new Gson();

        Shop shop = gson.fromJson(reader, Shop.class);
        return shop;
    }

    public static <T> String getJsonFromObj(T t) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(t);
    }

    public static String getJsonFromXml(String xmlFilePath) throws JAXBException {
        return getJsonFromObj(MyParser.getObjectFromXml(xmlFilePath));
    }


    //Save result to file writeToFile(Object obj, String typeFile (XML or JSON), String newFileName)
    /*public static void writeToFile(String newFileName) {
        String fullNewFileName = "src\\main\\resources\\" + newFileName;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(jsonFilePath), "utf-8"))) {
            writer.write(MyParser.getJsonFromXml(xmlFilePath));
        } catch (IOException ex) {
            System.err.println("Ошибка при работе с файлом для вывода: " + ex);
        }
    }*/


}
