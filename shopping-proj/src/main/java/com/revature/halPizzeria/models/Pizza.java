package com.revature.halPizzeria.models;


import com.revature.halPizzeria.daos.CrudDAO;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;

public class Pizza {

    private String id;
    private String pizza_name;
    private int price;
    private String pizza_desc;

    public Pizza(){
        super();
    }

    public Pizza(String id, String pizza_name, int price, String pizza_desc) {
        this.id = id;
        this.pizza_name = pizza_name;
        this.price = price;
        this.pizza_desc = pizza_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPizza_name() {
        return pizza_name;
    }

    public void setPizza_name(String pizza_name) {
        this.pizza_name = pizza_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPizza_desc() {
        return pizza_desc;
    }

    public void setPizza_desc(String pizza_desc) {
        this.pizza_desc = pizza_desc;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id='" + id + '\'' +
                ", pizza_name='" + pizza_name + '\'' +
                ", price='" + price + '\'' +
                ", pizza_desc='" + pizza_desc + '\'' +
                '}';
    }
}