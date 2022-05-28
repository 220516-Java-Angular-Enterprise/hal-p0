package com.revature.halPizzeria.models;

public class PizzaOrders {
    private String id;
    private String user_id;
    private String pizzeria_id;
    private String order_date;
    private int order_price;

    public PizzaOrders(String id, String user_id, String pizzeria_id, String order_date, int order_price) {
        this.id = id;
        this.user_id = user_id;
        this.pizzeria_id = pizzeria_id;
        this.order_date = order_date;
        this.order_price = order_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPizzeria_id() {
        return pizzeria_id;
    }

    public void setPizzeria_id(String pizzeria_id) {
        this.pizzeria_id = pizzeria_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    @Override
    public String toString() {
        return "PizzaOrders{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", pizzeria_id='" + pizzeria_id + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_price=" + order_price +
                '}';
    }
}
