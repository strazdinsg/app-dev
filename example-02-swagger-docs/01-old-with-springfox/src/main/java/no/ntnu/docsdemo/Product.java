package no.ntnu.docsdemo;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Data Transfer Object (DTO) - a product
 */
public class Product {
    @ApiModelProperty("Unique ID of the product")
    private int id;
    @ApiModelProperty("Name of the product")
    private String name;
    @ApiModelProperty("Price of the product, decimal in USD")
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
