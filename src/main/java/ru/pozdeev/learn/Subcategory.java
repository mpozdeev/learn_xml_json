package ru.pozdeev.learn;

import java.util.ArrayList;
import java.util.List;

public class Subcategory {
    private String name;
    private List<Product> productList;

    public Subcategory() {
        productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
