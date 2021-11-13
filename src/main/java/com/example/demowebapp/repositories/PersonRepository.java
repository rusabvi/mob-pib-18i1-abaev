package com.example.demowebapp.repositories;

import com.example.demowebapp.domain.Person;
import com.example.demowebapp.domain.Ware;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    private List<Person> persons = new ArrayList<>();
    private final Person admin;

    public PersonRepository() {
        admin = new Person(0, "admin", "Admin", 9999999);
        persons.add(new Person(100, "ivan","Ivan", 10000));
        persons.add(new Person(200, "petr", "Petr", 20000));
        persons.add(new Person(300, "sidor", "Sidor", 30000));
        persons.add(new Person(400, "john", "John", 40000));
    }

    public List<Person> getAllPersons() { return persons; }

    public Person getPersonById(int id) {
        for (Person p : persons)
            if (p.getId() == id)
                return p;
        return null;
    }

    public Person getAdmin() { return admin; }

    public void addPerson(Person person) { persons.add(person); }

    public void deletePerson(Person person) { persons.remove(person); }
}