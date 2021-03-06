package com.jhoncout.CheckpointIntegrador.controller;

import com.jhoncout.CheckpointIntegrador.dto.ProductsDTO;
import com.jhoncout.CheckpointIntegrador.model.Products;
import com.jhoncout.CheckpointIntegrador.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @PostMapping
    public ResponseEntity<Products> insert(@RequestBody ProductsDTO dao){
        try {
            return ResponseEntity.ok(service.insert(dao));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/all")
    public ResponseEntity<String> inserAll(@RequestBody List<ProductsDTO> list){
        list.forEach(this::insert);
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllProducts(){
        try {
            return ResponseEntity.ok(service.getAllProducts());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.getProductById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Buscar lista de produtos por categoria
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductsDTO>> getProductByCategory(@PathVariable String category){
        try {
            return ResponseEntity.ok(service.getProductByCategory(category));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Daqui pra baixo eu que quis terminar o CRUD

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@RequestBody ProductsDTO dao, Integer id){
        try {
            return ResponseEntity.ok(service.updateProduct(dao,id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.deleteProduct(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
