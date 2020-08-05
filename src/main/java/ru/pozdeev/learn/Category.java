package ru.pozdeev.learn;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Subcategory> subcategoryList;

    public Category() {
        subcategoryList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void setSubcategoryList(List<Subcategory> subcategoryList) {
        this.subcategoryList = subcategoryList;
    }

    public void addSubcategory(Subcategory subcategory) {
        subcategoryList.add(subcategory);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subcategoryList=" + subcategoryList +
                '}';
    }
}
