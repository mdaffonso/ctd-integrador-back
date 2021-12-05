package com.jhoncout.CheckpointIntegrador.service;

import com.jhoncout.CheckpointIntegrador.dao.ProductsDAO;
import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.model.Products;
import com.jhoncout.CheckpointIntegrador.repository.CategoriesRepository;
import com.jhoncout.CheckpointIntegrador.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private CategoriesRepository categoriesRepo;

    public Products insert(ProductsDAO dao){
        Categories category = categoriesRepo.findByName(dao.getCategory());

        Integer price = fixPrice(dao.getPrice());

        Products product = new Products(dao.getTitle(), price, dao.getDescription(), dao.getImage(), category);

        if(product.getTitle() == null && product.getPrice() == null){
            return null;
        }
        return productsRepo.save(product);
    }

    public List<Products> getAllProducts(){
        return productsRepo.findAll();
    }

    public Products getProductById(Integer id){
        return productsRepo.getById(id);
    }

    public List<Products> getProductByCategory(String category){
        return productsRepo.getProductsByCategory(category);
    }

    public Products updateProduct(ProductsDAO dao, Integer id){
        Products product = productsRepo.getById(id);

        Integer price = fixPrice(dao.getPrice());

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

    private Integer fixPrice(String priceString){
        String priceString1 = priceString.substring(0,priceString.length() - 3);
        String priceString2 = priceString.substring(priceString.length() - 2);

        return Integer.getInteger(priceString1 + priceString2);
    }
}
