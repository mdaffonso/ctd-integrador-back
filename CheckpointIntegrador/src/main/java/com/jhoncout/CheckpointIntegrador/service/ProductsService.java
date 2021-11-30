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
        Categories category = categoriesRepo.getById(dao.getCategories_id());

        Products product = new Products(dao.getTitle(), dao.getPrice(), dao.getDescription(), dao.getImage(), category);

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

        product.setPrice(dao.getPrice());
        product.setDescription(dao.getDescription());
        product.setImage(dao.getImage());
        product.setCategories(categoriesRepo.getById(dao.getCategories_id()));

        return productsRepo.save(product);
    }

    public String deleteProduct(Integer id){
        productsRepo.delete(productsRepo.getById(id));
        return "Removed Product successfully";
    }
}
