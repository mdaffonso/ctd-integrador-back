package com.jhoncout.CheckpointIntegrador.dto;

import com.jhoncout.CheckpointIntegrador.model.Products;

public class ProductsDTO {
    private Integer id;
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;

    public ProductsDTO(Products product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = convertPrice(product.getPrice());
        this.description = product.getDescription();
        this.image = product.getImage();
        this.category = product.getCategories().getName();
    }
    public ProductsDTO(){}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String convertPrice(Integer price){
        String priceString = price.toString();

        return priceString.substring(0,priceString.length()- 2) + "." + priceString.substring(priceString.length() - 2);
    }
}
