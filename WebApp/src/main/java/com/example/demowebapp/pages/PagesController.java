package com.example.demowebapp.pages;

import com.example.demowebapp.domain.Payment;
import com.example.demowebapp.domain.Person;
import com.example.demowebapp.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class PagesController {

    private final Random rnd = new Random();

    private final PersonRepository repository;

    public PagesController(PersonRepository repository) {
        this.repository = repository;
    }

    //GET http://localhost:8080/
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hello", "Hi there");
        //model.addAttribute("personPage", repository.findPersonById(100).getName());
        model.addAttribute("persons", repository.getAllPersons());
        return "index"; //index.html
    }

    //GET http://localhost:8080/person
    @GetMapping("/person/{id}")
    public String personPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", repository.getPersonById(id));
        return "person"; //person.html
    }

    @GetMapping("/person/{id}/payment")
    public String paymentPage(
            @PathVariable("id") int id,
            Model model
    ) {
        Person person = repository.getPersonById(id);
        model.addAttribute("person", person);
        model.addAttribute("payments", person.getAllPayments());
        return "payment";
    }

    @PostMapping("/person/{id}")
    public String editPerson(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("pay") int pay
    ) {
        Person person = repository.getPersonById(id);
        person.setName(name);
        person.setPay(pay);
        return "redirect:/person/" + id;
    }

    @PostMapping("/person/{id}/payment")
    public String createPayment(
            @PathVariable("id") int id,
            @RequestParam("pay") int pay,
            @RequestParam("month") String month,
            @RequestParam("year") String year
    ){

        Person person = repository.getPersonById(id);
        person.addPayment(new Payment(pay, month, year));
        return "redirect:/person/" + id + "/payment";
    }

    @PostMapping("/")//mine
    public String createPerson(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("pay") int pay
    ) {
        repository.addPerson(new Person(id, name, pay));
        return "redirect:/";
    }
}