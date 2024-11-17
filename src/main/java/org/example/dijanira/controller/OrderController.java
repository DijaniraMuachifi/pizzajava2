package org.example.dijanira.controller;

import org.example.dijanira.dao.OrderManager;
import org.example.dijanira.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderManager orderManager;


    @GetMapping
    public String getAllOrders(Model model) {
        List<Orders> orders = orderManager.getAll();
        model.addAttribute("orders", orders);
        return "orders/ListOrders";
    }


    @GetMapping("/add")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Orders(null, "", 0, new Timestamp(System.currentTimeMillis()), null));
        return "page/Orders";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Orders order) {
        if (order.getTaken() == null) {
            order.setTaken(new Timestamp(System.currentTimeMillis()));
        }
        orderManager.addOrder(order);
        return "redirect:/home";
    }


    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable long id, Model model) {
        List<Orders> orders = orderManager.getAll();
        Orders order = orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);

        model.addAttribute("order", order);
        return "orders/EditOrder";
    }

    @PostMapping("/edit")
    public String updateOrder(@ModelAttribute Orders order) {
        orderManager.updateOrder(order);
        return "redirect:/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable long id) {
        orderManager.deleteOrder(id);
        return "redirect:/orders";
    }
}
