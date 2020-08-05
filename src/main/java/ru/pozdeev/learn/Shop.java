package ru.pozdeev.learn;

import java.util.List;

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
}
