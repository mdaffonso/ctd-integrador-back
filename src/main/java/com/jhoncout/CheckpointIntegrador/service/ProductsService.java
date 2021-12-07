package com.jhoncout.CheckpointIntegrador.service;

import com.jhoncout.CheckpointIntegrador.dao.ProductsDTO;
import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.model.Products;
import com.jhoncout.CheckpointIntegrador.repository.CategoriesRepository;
import com.jhoncout.CheckpointIntegrador.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private CategoriesRepository categoriesRepo;

    public Products insert(ProductsDTO dao){
        Categories category = categoriesRepo.findByName(dao.getCategory());

        Integer price = fixPriceForPost(dao.getPrice());

        Products product = new Products(dao.getTitle(), price, dao.getDescription(), dao.getImage(), category);

        if(product.getTitle() == null && product.getPrice() == null){
            return null;
        }
        return productsRepo.save(product);
    }

    public List<ProductsDTO> getAllProducts(){
        return productsRepo.findAll().stream().map(ProductsDTO::new).collect(Collectors.toList());
    }

    public Products getProductById(Integer id){
        return productsRepo.getById(id);
    }

    public List<Products> getProductByCategory(String category){
        return productsRepo.getProductsByCategory(category);
    }

    public Products updateProduct(ProductsDTO dao, Integer id){
        Products product = productsRepo.getById(id);

        Integer price = fixPriceForPost(dao.getPrice());

        product.setPrice(price);
        product.setDescription(dao.getDescription());
        product.setImage(dao.getImage());
        product.setCategories(categoriesRepo.findByName(dao.getCategory()));

        return productsRepo.save(product);
    }

    public String deleteProduct(Integer id){
        productsRepo.delete(productsRepo.getById(id));
        return "Removed Product successfully";
    }

    private Integer fixPriceForPost(String priceString){
        if(!priceString.contains(".")){
            return Integer.valueOf(priceString + "00");
        }
            int dotIndex = priceString.indexOf(".");

            String priceString1 = priceString.substring(0,dotIndex);
            String priceString2 = priceString.substring(dotIndex + 1);

            if(priceString2.length() < 2) priceString2 = priceString2 + "0";

            return Integer.valueOf(priceString1 + priceString2);
    }
}
