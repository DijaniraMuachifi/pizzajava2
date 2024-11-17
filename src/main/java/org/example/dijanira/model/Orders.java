package org.example.dijanira.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class Orders {
    private Long  id;
    private String pizzaName;
    private int amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Define o formato esperado
    private Timestamp taken;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Define o formato esperado
    private Timestamp dispatched;

    public Orders(Long id, String pizzaName, int amount, Timestamp taken, Timestamp dispatched) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.amount = amount;
        this.taken = taken;
        this.dispatched = dispatched;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPizzaName() { return pizzaName; }
    public void setPizzaName(String pizzaName) { this.pizzaName = pizzaName; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public Timestamp getTaken() { return taken; }
    public void setTaken(Timestamp taken) { this.taken = taken; }

    public Timestamp getDispatched() { return dispatched; }
    public void setDispatched(Timestamp dispatched) { this.dispatched = dispatched; }
}
