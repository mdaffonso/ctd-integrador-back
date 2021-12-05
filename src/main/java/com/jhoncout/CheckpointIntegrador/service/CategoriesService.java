package com.jhoncout.CheckpointIntegrador.service;

import com.jhoncout.CheckpointIntegrador.model.Categories;
import com.jhoncout.CheckpointIntegrador.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repository;

    public Categories insert(Categories categories){
        return repository.save(categories);
    }

    public List<Categories> getAllCategories(){
        return repository.findAll();
    }

    public Categories getCategoryById(Integer id){
        return repository.getById(id);
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
