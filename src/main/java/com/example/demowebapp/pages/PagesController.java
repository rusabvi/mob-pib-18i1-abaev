package com.example.demowebapp.pages;

import com.example.demowebapp.domain.*;
import com.example.demowebapp.repositories.OperationRepository;
import com.example.demowebapp.repositories.PersonRepository;
import com.example.demowebapp.repositories.WareRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {
    private int activePersonId;
    private final PersonRepository personRepository;
    private final WareRepository wareRepository;
    private OperationRepository operationRepository;

    public PagesController(
            PersonRepository personRepository,
            WareRepository wareRepository,
            OperationRepository operationRepository
    ) {
        activePersonId = -1;
        this.personRepository = personRepository;
        this.wareRepository = wareRepository;
        this.operationRepository = operationRepository;
    }

    @GetMapping("/")
    public String loginPage() {
        activePersonId = -1;
        return "index";
    }

    @PostMapping("/")
    public String login(
            @RequestParam("id") int id,
            @RequestParam("password") String password
    ) {
        if (personRepository.getAdmin().getId() == id) {
            activePersonId = id;
            return "redirect:/adminPage";
        }
        if (personRepository.getPersonById(id) != null)
            if (password.equals(personRepository.getPersonById(id).getPassword()))
            {
                activePersonId = id;
                return "redirect:/shop";
            }
        return "redirect:/";
    }

    @GetMapping("/createPerson")
    public String createPersonPage() {
        if (activePersonId == personRepository.getAdmin().getId())
            return "redirect:/adminPage";
        if (personRepository.getPersonById(activePersonId) != null)
            return "redirect:/shop";
        return "createPerson";
    }

    @PostMapping("/createPerson")
    public String createPerson(
            @RequestParam("id") int id,
            @RequestParam("password") String password,
            @RequestParam("name") String name
    ) {
        if (personRepository.getPersonById(id) != null)
            return "redirect:/createPerson";
        personRepository.addPerson(new Person(id, password, name, 10000));
        activePersonId = id;
        return "redirect:/shop";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        if (activePersonId == personRepository.getAdmin().getId())
            return "redirect:/adminPage";
        if (personRepository.getPersonById(activePersonId) == null)
            return "redirect:/";
        model.addAttribute("person", personRepository.getPersonById(activePersonId));
        model.addAttribute("wares", wareRepository.getAllWares());
        return "shop";
    }

    @PostMapping("/shop")
    public String deletePerson() {
        personRepository.deletePerson(personRepository.getPersonById(activePersonId));
        return "redirect:/";
    }

    @GetMapping("/shop/{wareId}")
    public String buyingPage(
            @PathVariable("wareId") int wareId,
            Model model
    ) {
        if (activePersonId == personRepository.getAdmin().getId())
            return "redirect:/adminPage";
        if (personRepository.getPersonById(activePersonId) == null)
            return "redirect:/";
        model.addAttribute("ware", wareRepository.getWareByWareId(wareId));
        return "buying";
    }

    @PostMapping("/shop/{wareId}")
    public String buyWare(
            @PathVariable("wareId") int wareId,
            @RequestParam("amount") int amount
    ) {
        if (amount == 0)
            return "redirect:/shop/{wareId}";
        personRepository.getPersonById(activePersonId).subtractMoney(
                wareRepository.getWareByWareId(wareId).getPrice()
                        * amount
                        * Shop.getPriceCoefficient(personRepository.getPersonById(activePersonId))
        );
        wareRepository.getWareByWareId(wareId).subtractAmount(amount);
        personRepository.getPersonById(activePersonId).addWare(new Ware(
                wareId,
                wareRepository.getWareByWareId(wareId).getWareName(),
                wareRepository.getWareByWareId(wareId).getPrice(),
                amount
        ));
        operationRepository.addOperation(
                wareRepository.getWareByWareId(wareId),
                amount,
                personRepository.getPersonById(activePersonId)
        );
        return "redirect:/shop";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        if (activePersonId != personRepository.getAdmin().getId())
            if (personRepository.getPersonById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        model.addAttribute("persons", personRepository.getAllPersons());
        return "adminPage";
    }

    @GetMapping("/operations")
    public String operationsPage(Model model) {
        if (activePersonId != personRepository.getAdmin().getId())
            if (personRepository.getPersonById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        model.addAttribute("operations", operationRepository.getAllOperations());
        return "operations";
    }

    @GetMapping("/person/{id}")
    public String personPage(
            @PathVariable("id") int id,
            Model model
    ) {
        if (activePersonId == personRepository.getAdmin().getId() || activePersonId == id) {
            model.addAttribute("person", personRepository.getPersonById(id));
            model.addAttribute("personWares", personRepository.getPersonById(id).getAllPersonWares());
            return "person";
        }
        if (personRepository.getPersonById(activePersonId) != null)
            return "redirect:/shop";
        return "redirect:/";
    }

    @GetMapping("/statistics")
    public String statisticsPage(Model model) {
        if (activePersonId != personRepository.getAdmin().getId())
            if (personRepository.getPersonById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        Statistics statistics = new Statistics(operationRepository.getAllOperations());
        model.addAttribute("statistics", statistics);
        return "statistics";
    }
}