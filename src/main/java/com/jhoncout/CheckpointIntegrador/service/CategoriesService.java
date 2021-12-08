package com.jhoncout.CheckpointIntegrador.service;

import com.jhoncout.CheckpointIntegrador.dto.CategoriesDTO;
import com.jhoncout.CheckpointIntegrador.dto.ProductsDTO;
import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repository;

    public Categories insert(Categories categories){
        return repository.save(categories);
    }

    public List<CategoriesDTO> getAllCategories(){
        return repository.findAll().stream().map(CategoriesDTO::new).collect(Collectors.toList());
    }

    public CategoriesDTO getCategoryById(Integer id){
        return new CategoriesDTO(repository.getById(id));
    }

    public String removeCategory(Integer id){
        repository.delete(repository.getById(id));
        return "Categoria deletada";
    }

    public Categories updateCategory(Categories categories,Integer id){
        Categories category = repository.getById(id);
        category.setName(categories.getName());

        return repository.save(categories);
    }
}
