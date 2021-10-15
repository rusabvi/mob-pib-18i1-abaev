package com.example.demowebapp.repository;

import com.example.demowebapp.domain.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();

    public PersonRepository() {
        persons.add(new Person(100,"Ivanov"));
        persons.add(new Person(200,"Petrov"));
        persons.add(new Person(300, "Sidorov"));
        persons.add(new Person(400,"Loev")); // начальный список сотрудников
    }

    public List<Person> getAllPersons() { return persons; }

    public Person getPersonById(int id) {
        for (Person p : persons)
            if (p.getId() == id)
                return p;
        return null;
    }

    public void addPerson(Person person) { persons.add(person); }

    public void deletePerson(Person person) { persons.remove(person); }
}