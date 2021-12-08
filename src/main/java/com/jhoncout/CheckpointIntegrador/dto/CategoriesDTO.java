package com.jhoncout.CheckpointIntegrador.dto;

import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.model.Products;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesDTO {
    private Integer id;
    private String name;
    private List<ProductsDTO> products;

    public CategoriesDTO(Categories category){
        this.id = category.getId();
        this.name = category.getName();
        this.products = convertListProduct(category.getProducts());
    }
    public CategoriesDTO(){}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
    }

    private List<ProductsDTO> convertListProduct(List<Products> list){
        return list.stream().map(ProductsDTO::new).collect(Collectors.toList());
    }
}
