package com.example.Shop.repositories;

import com.example.Shop.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findById(int id);

    List<Person> findAll();

    List<Person> findByName(String name);
}