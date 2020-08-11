package ru.pozdeev.learn;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Shop {

    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "categoryList=" + categoryList +
                '}';
    }

    public void showShop() {
        for (Category category : getCategoryList()) {
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
