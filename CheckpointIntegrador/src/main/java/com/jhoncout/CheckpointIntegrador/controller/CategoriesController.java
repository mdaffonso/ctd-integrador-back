package com.jhoncout.CheckpointIntegrador.controller;

import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService service;

    @PostMapping
    public ResponseEntity<Categories> insert(@RequestBody Categories categories){
        try {
            return ResponseEntity.ok(service.insert(categories));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories(){
        try {
            return ResponseEntity.ok(service.getAllCategories());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.getCategoryById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.removeCategory(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@RequestBody Categories categories, @PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.updateCategory(categories,id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
