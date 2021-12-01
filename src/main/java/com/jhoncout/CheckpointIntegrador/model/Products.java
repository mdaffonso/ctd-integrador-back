package com.jhoncout.CheckpointIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Products {

    @Id
    @SequenceGenerator(name = "product_sequence",sequenceName = "product_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Integer id;

    private String title;
    private Double price;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    public Products(String title, Double price, String description, String image, Categories categories) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categories = categories;
    }
    public Products(){}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }


}
