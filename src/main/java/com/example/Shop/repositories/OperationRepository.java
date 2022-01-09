package com.example.Shop.repositories;

import com.example.Shop.entities.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAll();

    @Query(
            "SELECT p.name, SUM(o.price), round(AVG(o.price), 2) " +
            "FROM Person AS p, Operation As o " +
            "WHERE o.buyer = p.id " +
            "GROUP BY p.name"
    )
    List<Object> selectBuyerNameAndSumOfPriceAndAvgOfPriceByBuyerName();

    @Query(
            "SELECT w.name, SUM(o.price), round(AVG(o.price), 2) " +
            "FROM Ware AS w, Operation As o " +
            "WHERE o.ware = w.id " +
            "GROUP BY w.name"
    )
    List<Object> selectWareNameAndSumOfPriceAndAvgOfPriceByWareName();

    @Query(
            "SELECT o.date, SUM(o.price), round(AVG(o.price), 2) " +
            "FROM Operation As o " +
            "GROUP BY o.date"
    )
    List<Object> selectDateAndSumOfPriceAndAvgOfPriceByDate();

    @Query(
            "SELECT o.date, COUNT(DISTINCT o.id), COUNT(DISTINCT o.buyer.id), " +
            "COUNT(DISTINCT o.ware.id), round(AVG(o.price), 2), SUM(o.price), " +
            "(SUM(o.price) * 0.13), (SUM(o.price) * 0.87) " +
            "FROM Operation As o " +
            "GROUP BY o.date"
    )
    List<Object> selectDataForReport();
}