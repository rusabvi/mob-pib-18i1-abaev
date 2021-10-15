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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hello", "Hi there");
        model.addAttribute("persons", personRepository.getAllPersons());
        return "index";
    }

    @GetMapping("/person/{id}")
    public String personPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payments", personRepository.getPersonById(id).getAllPayments());
        return "person";
    }

    @GetMapping("/person/{id}/editPerson")
    public String editPersonPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        return "editPerson";
    }

    @GetMapping("/person/{id}/deletePerson")
    public String deletePersonPage(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        return "deletePerson";
    }

    @GetMapping("/person/{id}/payment/{payId}")
    public String paymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "payment";
    }

    @GetMapping("/person/{id}/payment/{payId}/editPayment")
    public String editPaymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "editPayment";
    }

    @GetMapping("/person/{id}/payment/{payId}/deletePayment")
    public String deletePaymentPage(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            Model model
    ) {
        model.addAttribute("person", personRepository.getPersonById(id));
        model.addAttribute("payment", personRepository.getPersonById(id).getPaymentByPayId(payId));
        return "deletePayment";
    }

    @PostMapping("/")
    public String createPerson(
            @RequestParam("id") int id,
            @RequestParam("name") String name
    ) {
        personRepository.addPerson(new Person(id, name));
        return "redirect:/";
    }

    @PostMapping("/person/{id}")
    public String createPayment(
            @PathVariable("id") int id,
            @RequestParam("payId") int payId,
            @RequestParam("payBeforeNDFL") int payBeforeNDFL,
            @RequestParam("month") int month,
            @RequestParam("year") int year
    ){
        personRepository.getPersonById(id).addPayment(new Payment(payId, payBeforeNDFL, month, year));
        return "redirect:/person/" + id;
    }

    @PostMapping("/person/{id}/editPerson")
    public String editPerson(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        personRepository.getPersonById(id).setName(name);
        return "redirect:/person/" + id + "/editPerson";
    }

    @PostMapping("/person/{id}/deletePerson")
    public String deletePerson(
            @PathVariable("id") int id
    ) {
        personRepository.deletePerson(personRepository.getPersonById(id));
        return "redirect:/";
    }

    @PostMapping("/person/{id}/payment/{payId}/editPayment")
    public String editPayment(
            @PathVariable("id") int id,
            @PathVariable("payId") int payId,
            @RequestParam("payBeforeNDFL") int payBeforeNDFL,
            @RequestParam("month") int month,
            @RequestParam("year") int year
    ) {
        /*personRepository.getPersonById(id).editPayment(
                personRepository.getPersonById(id).getPaymentByPayId(payId),
                payBeforeNDFL,
                month,
                year
        );*/
        personRepository.getPersonById(id).getPaymentByPayId(payId).setPayBeforeNDFL(payBeforeNDFL);
        personRepository.getPersonById(id).getPaymentByPayId(payId).setMonth(month);
        personRepository.getPersonById(id).getPaymentByPayId(payId).setYear(year);
        return "redirect:/person/" + id + "/payment/" + payId + "/editPayment";
    }

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