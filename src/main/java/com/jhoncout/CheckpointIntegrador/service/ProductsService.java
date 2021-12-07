package com.jhoncout.CheckpointIntegrador.service;

import com.jhoncout.CheckpointIntegrador.dao.ProductsDAO;
import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.model.Products;
import com.jhoncout.CheckpointIntegrador.repository.CategoriesRepository;
import com.jhoncout.CheckpointIntegrador.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private CategoriesRepository categoriesRepo;

    public Products insert(ProductsDAO dao){
        Categories category = categoriesRepo.findByName(dao.getCategory());

        Integer price = fixPriceForPost(dao.getPrice());

        Products product = new Products(dao.getTitle(), price, dao.getDescription(), dao.getImage(), category);

        if(product.getTitle() == null && product.getPrice() == null){
            return null;
        }
        return productsRepo.save(product);
    }

    public List<ProductsDAO> getAllProducts(){
        List<Products> list = productsRepo.findAll();
        List<ProductsDAO> listDAO = new ArrayList<>();

        list.forEach(product -> {
            ProductsDAO productDAO = new ProductsDAO();
            String priceString = fixPriceForGet(product.getPrice());

            productDAO.setTitle(product.getTitle());
            productDAO.setPrice(priceString);
            productDAO.setDescription(product.getDescription());
            productDAO.setImage(product.getImage());
            productDAO.setCategory(product.getCategories().getName());

            listDAO.add(productDAO);
        });

        return listDAO;
    }

    public Products getProductById(Integer id){
        return productsRepo.getById(id);
    }

    public List<Products> getProductByCategory(String category){
        return productsRepo.getProductsByCategory(category);
    }

    public Products updateProduct(ProductsDAO dao, Integer id){
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

    private String fixPriceForGet(Integer price){
        String priceString = price.toString();

        return priceString.substring(0,priceString.length()- 2) + "." + priceString.substring(priceString.length() - 2);
    }
}
