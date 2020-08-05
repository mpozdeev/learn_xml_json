package ru.pozdeev.learn;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement
@XmlType(name = "product")
public class Product {

    private String manufacturer;
    private String model;
    private String dateOfManufacture;
    private String color;
    private float price;
    private int quantity;

    //  Constructors

    public Product() {
    }

    public Product(String manufacturer, String model, String dateOfManufacture, String color, float price, int quantity) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.dateOfManufacture = dateOfManufacture;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    //  Getters, Setters

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", dateOfManufacture='" + dateOfManufacture + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return manufacturer.equals(product.manufacturer) &&
                model.equals(product.model) &&
                dateOfManufacture.equals(product.dateOfManufacture) &&
                color.equals(product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, dateOfManufacture, color);
    }
}
