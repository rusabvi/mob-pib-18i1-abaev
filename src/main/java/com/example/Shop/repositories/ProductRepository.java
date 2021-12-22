package com.example.Shop.repositories;

import com.example.Shop.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(int id);

    List<Product> findAll();
}