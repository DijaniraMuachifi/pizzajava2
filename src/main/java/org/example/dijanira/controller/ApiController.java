package org.example.dijanira.controller;

import org.example.dijanira.dao.CategoryManager;
import org.example.dijanira.model.User;
import org.example.dijanira.model.Category;
import org.example.dijanira.dao.OrderManager;
import org.example.dijanira.dao.PizzaManager;
import org.example.dijanira.dao.UserManager;
import org.example.dijanira.model.Orders;
import org.example.dijanira.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private PizzaManager pizzaManager;

    @Autowired
    private CategoryManager categoryManager;

    @Autowired
    private UserManager userManager;

    // --------------------- Endpoints para Orders ---------------------

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return orderManager.getAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = orderManager.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        orderManager.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {
        Orders order = orderManager.getOrderById(id);
        if (order != null) {
            order.setPizzaName(updatedOrder.getPizzaName());
            order.setAmount(updatedOrder.getAmount());
            order.setTaken(updatedOrder.getTaken());
            order.setDispatched(updatedOrder.getDispatched());
            orderManager.updateOrder(order);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Orders order = orderManager.getOrderById(id);
        if (order != null) {
            orderManager.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --------------------- Endpoints para Pizzas ---------------------

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaManager.getAll();
    }

    @GetMapping("/pizzas/{name}")
    public ResponseEntity<Pizza> getPizzaByName(@PathVariable String name) {
        Pizza pizza = pizzaManager.getPizzaByName(name);
        return pizza != null ? ResponseEntity.ok(pizza) : ResponseEntity.notFound().build();
    }

    @PostMapping("/pizzas")
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        pizzaManager.addPizza(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizza);
    }

    @PutMapping("/pizzas/{name}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable String name, @RequestBody Pizza updatedPizza) {
        Pizza pizza = pizzaManager.getPizzaByName(name);
        if (pizza != null) {
            pizza.setCategoryname(updatedPizza.getCategoryname());
            pizza.setVegetarian(updatedPizza.getVegetarian());
            pizzaManager.updatePizza(pizza);
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/pizzas/{name}")
    public ResponseEntity<Void> deletePizza(@PathVariable String name) {
        Pizza pizza = pizzaManager.getPizzaByName(name);
        if (pizza != null) {
            pizzaManager.deletePizza(name);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --------------------- Endpoints para Categories ---------------------

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryManager.getAll();
    }
/*
    @GetMapping("/categories/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryManager.getCategoryByName(name);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

 */

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categoryManager.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
/*
    @PutMapping("/categories/{name}")
    public ResponseEntity<Category> updateCategory(@PathVariable String name, @RequestBody Category updatedCategory) {
        Category category = categoryManager.getCategoryByName(name);
        if (category != null) {
            category.setPrice(updatedCategory.getPrice());
            categoryManager.updateCategory(category);
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }
    *
 */

    @DeleteMapping("/categories/{name}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String name) {

        if (name != null) {
            categoryManager.deleteCategory(name);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --------------------- Endpoints para Users ---------------------

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userManager.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userManager.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userManager.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }



}

