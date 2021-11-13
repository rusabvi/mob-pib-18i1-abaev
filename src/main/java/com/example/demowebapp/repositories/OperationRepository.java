package com.example.demowebapp.repositories;

import com.example.demowebapp.domain.Operation;
import com.example.demowebapp.domain.Person;
import com.example.demowebapp.domain.Ware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationRepository {
    private List<Operation> operations = new ArrayList<>();

    public List<Operation> getAllOperations() { return operations; }

    public void addOperation(Ware ware, int amount, Person person) {
        operations.add(new Operation(operations.size(), ware, amount, person));
    }
}
