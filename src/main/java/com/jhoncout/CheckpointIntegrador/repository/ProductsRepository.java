package com.jhoncout.CheckpointIntegrador.repository;
import com.jhoncout.CheckpointIntegrador.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

    @Query("select p from Products p where p.categories = ?1")
    List<Products> getProductsByCategory(String category);
}
