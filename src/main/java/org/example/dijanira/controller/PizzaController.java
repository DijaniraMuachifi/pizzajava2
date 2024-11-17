package org.example.dijanira.controller;

import org.example.dijanira.dao.PizzaManager;
import org.example.dijanira.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PizzaController {

    @Autowired
    private PizzaManager pizzaManager;

    @GetMapping("/add-pizza")
    public String showAddPizzaForm(Model model) {
        return "page/AddPizza";
    }

    @PostMapping("/add-pizza")
    public String addPizza(
            @RequestParam String category,
            @RequestParam String name,
            @RequestParam String vegan,
            Model model) {

        Pizza pizza = new Pizza(name, category, "yes".equals(vegan));
        pizzaManager.addPizza(pizza);
        model.addAttribute("message", "Pizza added successfully!");
        return "redirect:/home"; // Redireciona para a página inicial
    }

    @GetMapping("/update-pizza/{pname}")
    public String showUpdatePizzaForm(@PathVariable String pname, Model model) {
        Pizza pizza = pizzaManager.getPizzaByName(pname);
        model.addAttribute("pizza", pizza);
        return "page/UpdatePizza"; // Página de atualização
    }

    @PostMapping("/update-pizza/{pname}")
    public String updatePizza(
            @PathVariable String pname,
            @RequestParam String category,
            @RequestParam String vegan,
            Model model) {

        Pizza pizza = new Pizza(pname, category, "yes".equals(vegan));
        pizzaManager.updatePizza(pizza);
        model.addAttribute("message", "Pizza updated successfully!");
        return "redirect:/home"; // Redireciona para a página inicial
    }

    @GetMapping("/delete-pizza/{pname}")
    public String deletePizza(@PathVariable String pname, Model model) {
        pizzaManager.deletePizza(pname);
        model.addAttribute("message", "Pizza deleted successfully!");
        return "redirect:/home";
    }

    @GetMapping("/all-pizzas")
    public String listPizzas(Model model) {
        model.addAttribute("pizzas", pizzaManager.getAll());
        return "page/ListPizzas";
    }
}
