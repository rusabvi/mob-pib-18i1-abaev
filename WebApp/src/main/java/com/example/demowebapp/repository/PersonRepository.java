package com.example.demowebapp.repository;

import com.example.demowebapp.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    private final List<Person> persons = List.of(
            new Person(100,"Ivanov", 10_000),
            new Person(200,"Petrov", 11_000),
            new Person(300, "Sidorov", 12_000),
            new Person(400,"Aboba", 15_000)
    );

    public List<Person> getAllPersons() {
        return persons;
    }

    public Person getPersonById(int id) {
        Person foundPerson = null;
        for (Person p : persons) {
            if (p.getId() == id) {
                foundPerson = p;
            }
        }
        return foundPerson;
    }

    public void addPerson(Person person) { persons.add(person); }
}