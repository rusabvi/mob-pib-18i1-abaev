package com.example.Shop.pages;

import com.example.Shop.domain.Shop;
import com.example.Shop.domain.Statistics;
import com.example.Shop.entities.Operation;
import com.example.Shop.entities.Person;
import com.example.Shop.repositories.OperationRepository;
import com.example.Shop.repositories.PersonRepository;
import com.example.Shop.repositories.ProductRepository;
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
    private final ProductRepository productRepository;
    private final OperationRepository operationRepository;

    public PagesController(
            PersonRepository personRepository,
            ProductRepository productRepository,
            OperationRepository operationRepository
    ) {
        activePersonId = -1;
        this.personRepository = personRepository;
        this.productRepository = productRepository;
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
        if (id == 0 && password.equals("admin")) {
            activePersonId = id;
            return "redirect:/admin";
        }
        if (personRepository.findById(id) != null)
            if (password.equals(personRepository.findById(id).getPassword()))
            {
                activePersonId = id;
                return "redirect:/shop";
            }
        return "redirect:/";
    }

    @GetMapping("/createPerson")
    public String createPersonPage() {
        if (activePersonId == 0)
            return "redirect:/admin";
        if (personRepository.findById(activePersonId) != null)
            return "redirect:/shop";
        return "createPerson";
    }

    @PostMapping("/createPerson")
    public String createPerson(
            @RequestParam("id") int id,
            @RequestParam("password") String password,
            @RequestParam("name") String name
    ) {
        if (personRepository.findById(id) != null)
            return "redirect:/createPerson";
        personRepository.save(new Person(id, password, name, 10000));
        activePersonId = id;
        return "redirect:/shop";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        if (activePersonId == 0)
            return "redirect:/admin";
        if (personRepository.findById(activePersonId) == null)
            return "redirect:/";
        model.addAttribute("person", personRepository.findById(activePersonId));
        model.addAttribute("products", productRepository.findAll());
        return "shop";
    }

    @PostMapping("/shop")
    public String deletePerson() {
        personRepository.delete(personRepository.findById(activePersonId));
        return "redirect:/";
    }

    @GetMapping("/shop/{id}")
    public String buyingPage(
            @PathVariable("id") int id,
            Model model
    ) {
        if (activePersonId == 0)
            return "redirect:/admin";
        if (personRepository.findById(activePersonId) == null)
            return "redirect:/";
        if (productRepository.findById(id) == null)
            return "redirect:/shop";
        model.addAttribute("product", productRepository.findById(id));
        return "buying";
    }

    @PostMapping("/shop/{id}")
    public String buyProduct(
            @PathVariable("id") int id,
            @RequestParam("amount") int amount
    ) {
        if (amount == 0
                || personRepository.findById(activePersonId).getMoney()
                < productRepository.findById(id).getWare().getPrice()
                * amount
                * Shop.getPriceCoefficient(personRepository.findById(activePersonId)))
            return "redirect:/shop/{id}";
        personRepository.findById(activePersonId).subtractMoney(
                productRepository.findById(id).getWare().getPrice()
                        * amount
                        * Shop.getPriceCoefficient(personRepository.findById(activePersonId))
        );
        productRepository.findById(id).subtractAmount(amount);
        operationRepository.save(new Operation(
                0,
                productRepository.findById(id).getWare(),
                amount,
                personRepository.findById(activePersonId)
        ));
        return "redirect:/shop";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        if (activePersonId != 0)
            if (personRepository.findById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        model.addAttribute("persons", personRepository.findAll());
        return "admin";
    }

    @GetMapping("/operations")
    public String operationsPage(Model model) {
        if (activePersonId != 0)
            if (personRepository.findById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        model.addAttribute("statistics", new Statistics(operationRepository.findAll()));
        model.addAttribute("operations", operationRepository.findAll());
        return "operations";
    }

    @GetMapping("/operations/groupedBy{filter}")
    public String groupedOperations(
            @PathVariable("filter") String filter,
            Model model
    ) {
        if (activePersonId != 0)
            if (personRepository.findById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        if (filter.equals("Buyer"))
            model.addAttribute("GB", operationRepository.selectBuyerNameAndSumOfPriceAndAvgOfPriceByBuyerName());
        else if (filter.equals("Ware"))
            model.addAttribute("GB", operationRepository.selectWareNameAndSumOfPriceAndAvgOfPriceByWareName());
        else
            model.addAttribute("GB", operationRepository.selectDateAndSumOfPriceAndAvgOfPriceByDate());
        model.addAttribute("filter", filter);
        return "groupedOperations";
    }

    @GetMapping("/operations/report")
    public String report(Model model) {
        if (activePersonId != 0)
            if (personRepository.findById(activePersonId) != null)
                return "redirect:/shop";
            else
                return "redirect:/";
        model.addAttribute("reportData", operationRepository.selectDataForReport());
        return "report";
    }
}