package com.revature.halPizzeria.models;

public class PizzeriaInventory {
    private String pizzeria_id;
    private String pizza_id;
    private int quantity;

    public PizzeriaInventory(String pizzeria_id, String pizza_id, int quantity) {
        this.pizzeria_id = pizzeria_id;
        this.pizza_id = pizza_id;
        this.quantity = quantity;
    }

    public String getPizzeria_id() {
        return pizzeria_id;
    }

    public void setPizzeria_id(String pizzeria_id) {
        this.pizzeria_id = pizzeria_id;
    }

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "PizzeriaInventory{" +
                "pizzeria_id='" + pizzeria_id + '\'' +
                ", pizza_id='" + pizza_id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
