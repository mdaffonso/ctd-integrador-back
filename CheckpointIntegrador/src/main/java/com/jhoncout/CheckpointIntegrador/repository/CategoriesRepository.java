package com.jhoncout.CheckpointIntegrador.repository;

import com.jhoncout.CheckpointIntegrador.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}
