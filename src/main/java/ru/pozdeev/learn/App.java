package ru.pozdeev.learn;

import javax.xml.bind.JAXBException;

public class App {
    public static void main(String[] args) throws JAXBException {
        String xmlFilePath = "src\\main\\resources\\product.xml";
        String jsonFilePath = "src\\main\\resources\\new_product.json";
        String schemaName = "src\\main\\resources\\run_schema.xsd";

        // XML -> Obj
//        Shop shopFromXml = MyParser.getObjectFromXml(xmlFilePath);
//        shopFromXml.showShop();

        //Obj -> XML
//        Shop shop = MyParser.getObjectFromXml(xmlFilePath);
//        System.out.println(MyParser.getXmlFromObj(shop));

        //JSON -> Obj
//        Shop shopFromJson = MyParser.getObjectFromJson(jsonFilePath);
//        shopFromJson.showShop();

        //Obj -> JSON
//        Shop shop = MyParser.getObjectFromJson(jsonFilePath);
//        System.out.println(MyParser.getJsonFromObj(shop));

        //XML -> JSON
//        System.out.println(MyParser.getJsonFromXml(xmlFilePath));

        //JSON -> XML
//        System.out.println(MyParser.getXmlFromJson(jsonFilePath));

        if (ValidatorXml.isValid(xmlFilePath, schemaName)) {
            System.out.println("valid");
        } else{
            System.out.println("not valid - see staectrase");
        }

    }
}
