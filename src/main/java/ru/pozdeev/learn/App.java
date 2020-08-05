package ru.pozdeev.learn;

import javax.xml.bind.JAXBException;

public class App {
    public static void main(String[] args) throws JAXBException {
        String xmlFilePath = "src\\main\\resources\\product.xml";

        Shop shop = StAX_JAXB_Parser.parseXMLfile(xmlFilePath);
        for (Category category : shop.getCategoryList()) {
            System.out.println("Category name: " + category.getName());
            for (Subcategory subcategory : category.getSubcategoryList()) {
                System.out.println("    Subcategory name: " + subcategory.getName());
                for (Product product : subcategory.getProductList()) {
                    System.out.println("        Product: " + product.toString());
                }
            }
            System.out.println("---------------------------------------------");
        }
    }
}
