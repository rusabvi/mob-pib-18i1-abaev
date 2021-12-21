package com.example.Shop.repositories;

import com.example.Shop.entities.Operation;
import com.example.Shop.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAll();

    //SELECT age, SUM(salary) as sum FROM workers GROUP BY age
    @Query("SELECT p.name, SUM(o.price), AVG(o.price) FROM Person AS p, Operation As o WHERE o.buyer = p.id GROUP BY p.name")
    List<Object> selectBuyerNameAndSumOfPriceAndAvgOfPriceByBuyerName();

    @Query("SELECT w.name, SUM(o.price), AVG(o.price) FROM Ware AS w, Operation As o WHERE o.ware = w.id GROUP BY w.name")
    List<Object> selectWareNameAndSumOfPriceAndAvgOfPriceByWareName();

    @Query("SELECT o.date, SUM(o.price), AVG(o.price) FROM Operation As o GROUP BY o.date")
    List<Object> selectDateAndSumOfPriceAndAvgOfPriceByDate();

    //@Query("SELECT c.year, COUNT(c.year) FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")
    //List<Object[]> countTotalCommentsByYear();
    //https://www.baeldung.com/jpa-queries-custom-result-with-aggregation-functions

    //@Query("update User u set u.firstname = ?1 where u.lastname = ?2")
    //int setFixedFirstnameFor(String firstname, String lastname);

    //List<Operation> findAllGroupByName(String name);
    //group by name
    //by ware
    //@Query("SELECT u FROM User u WHERE u.status = 1")
    //Collection<User> findAllActiveUsers();
    //by date
    //by
}
