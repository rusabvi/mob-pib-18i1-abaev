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
    private PersonRepository personRepository;

    public PagesController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // выводит список сотрудников со ссылками
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hello", "Hi there");
        //model.addAttribute("personPage", personRepository.findPersonById(100).getName());
        model.addAttribute("persons", personRepository.getAllPersons());
        return "index"; //index.html
    }

    // переводит на выбранного сотрудника
    @GetMapping("/person/{id}")
    public String personPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payments", personRepository.getPersonById(id).getAllPayments());
        return "person"; //person.html
    }

    // показывает страницу editPerson
    @GetMapping("/person/{id}/editPerson")
    public String editPersonPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        return "editPerson"; //editPerson.html
    }

    // показывает страницу deletePerson
    @GetMapping("/person/{id}/deletePerson")
    public String deletePersonPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        return "deletePerson"; //deletePerson.html
    }

    // переводит на выбранное начисление - проблема
    @GetMapping("/person/{id}/payment/{payId}")
    public String paymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "payment"; //payment.html
    }

    // показывает страницу editPayment
    @GetMapping("/person/{id}/payment/{payId}/editPayment")
    public String editPaymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "editPayment"; //editPayment.html
    }

    // показывает страницу deletePerson
    @GetMapping("/person/{id}/payment/{payId}/deletePayment")
    public String deletePaymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "deletePayment"; //deletePayment.html
    }

    // создаёт нового сотрудника
    @PostMapping("/")
    public String createPerson(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("pay") int pay) {
        personRepository.addPerson(new Person(id, name, pay));
        return "redirect:/";
    }

    // создаёт новое начисление
    @PostMapping("/person/{id}")
    public String createPayment(
            @PathVariable("id") int id,
            @RequestParam("payId") int payId,
            @RequestParam("pay") int pay,
            @RequestParam("month") String month,
            @RequestParam("year") String year
    ){
        personRepository.getPersonById(id).addPayment(new Payment(payId, pay, month, year));
        return "redirect:/person/" + id;
    }

    // позволяет изменить данные сотрудника
    @PostMapping("/person/{id}/editPerson")
    public String editPerson(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("pay") int pay
    ) {
        personRepository.getPersonById(id).setName(name);
        personRepository.getPersonById(id).setPay(pay);
        return "redirect:/person/" + id + "/editPerson";
    }

    // позволяет удалить данные сотрудника
    @PostMapping("/person/{id}/deletePerson")
    public String deletePerson(
            @PathVariable("id") int id
    ) {
        personRepository.deletePerson(personRepository.getPersonById(id));
        return "redirect:/";
    }

    // позволяет изменить данные начисления
    @PostMapping("/person/{id}/payment/{payId}/editPayment")
    public String editPayment(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            @RequestParam("pay") int pay,
            @RequestParam("month") String month,
            @RequestParam("year") String year
    ) {
        personRepository.getPersonById(id).editPayment(
                personRepository.getPersonById(id).getPaymentByPayId(payId),
                pay,
                month,
                year
        );
        return "redirect:/person/" + id + "/payment/" + payId + "/editPayment";
    }

    // позволяет удалить начисление
    @PostMapping("/person/{id}/payment/{payId}/deletePayment")
    public String deletePayment(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId
    ) {
        personRepository.getPersonById(id).deletePayment(
                personRepository.getPersonById(id).getPaymentByPayId(payId)
        );
        return "redirect:/person/" + id;
    }
}