package com.revature.halPizzeria.models;

public class OrderedPizzas {
    private String order_id;
    private String pizza_id;
    private int pizza_quantity;

    public OrderedPizzas(String order_id, String pizza_id, int pizza_quantity) {
        this.order_id = order_id;
        this.pizza_id = pizza_id;
        this.pizza_quantity = pizza_quantity;
    }

    public OrderedPizzas() {

    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public int getPizza_quantity() {
        return pizza_quantity;
    }

    public void setPizza_quantity(int pizza_quantity) {
        this.pizza_quantity = pizza_quantity;
    }

    @Override
    public String toString() {
        return "OrderedPizzas{" +
                "order_id='" + order_id + '\'' +
                ", pizza_id='" + pizza_id + '\'' +
                ", pizza_quantity=" + pizza_quantity +
                '}';
    }
}
