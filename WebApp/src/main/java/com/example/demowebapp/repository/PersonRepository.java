package com.example.demowebapp.repository;

import com.example.demowebapp.domain.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();

    // создаёт начальный список сотрудников
    public PersonRepository() {
        persons.add(new Person(100,"Ivanov", 10_000));
        persons.add(new Person(200,"Petrov", 11_000));
        persons.add(new Person(300, "Sidorov", 12_000));
        persons.add(new Person(400,"Loev", 0_000));
    }

    // передаёт список сотрудников
    public List<Person> getAllPersons() { return persons; }

    // передаёт сотрудника с определённым id, либо null
    public Person getPersonById(int id) {
        for (Person p : persons)
            if (p.getId() == id)
                return p;
        return null;
    }

    // создаёт нового сотрудника
    public void addPerson(Person person) { persons.add(person); }

    public void deletePerson(Person person) { persons.remove(person); }
}