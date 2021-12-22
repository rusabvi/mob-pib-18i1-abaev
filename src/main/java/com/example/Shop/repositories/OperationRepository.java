package com.example.Shop.repositories;

import com.example.Shop.entities.Operation;
import com.example.Shop.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAll();

    @Query("SELECT p.name, SUM(o.price), AVG(o.price) FROM Person AS p, Operation As o WHERE o.buyer = p.id GROUP BY p.name")
    List<Object> selectBuyerNameAndSumOfPriceAndAvgOfPriceByBuyerName();

    @Query("SELECT w.name, SUM(o.price), AVG(o.price) FROM Ware AS w, Operation As o WHERE o.ware = w.id GROUP BY w.name")
    List<Object> selectWareNameAndSumOfPriceAndAvgOfPriceByWareName();

    @Query("SELECT o.date, SUM(o.price), AVG(o.price) FROM Operation As o GROUP BY o.date")
    List<Object> selectDateAndSumOfPriceAndAvgOfPriceByDate();
}