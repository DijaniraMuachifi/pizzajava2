package org.example.dijanira.controller;

import org.example.dijanira.dao.*;
import org.example.dijanira.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PizzaManager pizzaManager;
    @Autowired
    private ContactMessageManager contactMessageManager; // Add ContactMessageManager

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private UserManager userManager;

    // Default constructor for dependency injection
    public HomeController() {
    }

    // Constructor for testing or specific injection scenarios
    public HomeController(PizzaManager pizzaManager) {
        this.pizzaManager = pizzaManager;
    }

    @GetMapping("/") // Home page
    public String home(Model model) {
        // Fetch all pizzas
        List<Pizza> pizzas = pizzaManager.getAll();


        // Add pizzas to the model
        model.addAttribute("pizzas", pizzas);

        return "page/Home";
    }
    @GetMapping("/contact") // Home page
    public String contact() {

        return "page/contact";
    }
    @PostMapping("/contact") // Handle form submission
    public String handleContactForm(@RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("message") String message,
                                    Model model) {
        // Validate inputs here (e.g., check for empty fields, valid email, etc.)
        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "page/contact"; // Return the form with error message
        }

        // Save the contact message in the database
        ContactMessage contactMessage = new ContactMessage(name, email, message);
        contactMessageManager.saveMessage(contactMessage);

        // Success message
        model.addAttribute("success", "Your message has been sent successfully.");
        return "page/contact"; // Return the form with success message
    }
    @GetMapping("/home") // Data page with tabs
    public String index(Model model) {
        // Fetch data from DAOs
        List<Pizza> pizzas = pizzaManager.getAll();
        List<Orders> orders = orderManager.getAll();
        List<Category> categories = categoryManager.getAll();
        List<User> users=userManager.getAll();

        // Add data to the model
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("orders", orders);
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);

            System.out.println("Username: " + username);
            model.addAttribute("username", username);
            model.addAttribute("role", role);
        } else {
            System.out.println("No authenticated user");
        }

        return "page/auh"; // Return the name of the Thymeleaf template for tabs
    }
    @GetMapping("/about")
    public String about() {

        return "page/about";
    }
    @GetMapping("/insert")
    public String insert(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);

            System.out.println("Username: " + username);
            model.addAttribute("username", username);
            model.addAttribute("role", role);
        } else {
            System.out.println("No authenticated user");
        }
        return "page/insert";
    }

    @GetMapping("/message")
    public String commit(Model model) {

        List<ContactMessage> contactMessages = contactMessageManager.getAll();

        model.addAttribute("contactMessages", contactMessages);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);

            System.out.println("Username: " + username);
            model.addAttribute("username", username);
            model.addAttribute("role", role);
        } else {
            System.out.println("No authenticated user");
        }
        return "page/commit";
    }

}
