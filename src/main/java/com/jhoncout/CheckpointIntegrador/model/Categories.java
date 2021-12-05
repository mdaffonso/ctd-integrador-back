package com.jhoncout.CheckpointIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categories {

    @Id
    @SequenceGenerator(name = "categories_sequence",sequenceName = "categories_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_sequence")
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "categories")
    private List<Products> products;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
