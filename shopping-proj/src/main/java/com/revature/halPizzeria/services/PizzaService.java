package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzaDAO;
import com.revature.halPizzeria.models.Pizza;

import java.util.List;

public class PizzaService {
    private final PizzaDAO pizzaDAO;

    public PizzaService(PizzaDAO pizzaDAO){
        this.pizzaDAO = pizzaDAO;
    }

    public List<Pizza> getPizzasByPizzeriaId(String id){
        return pizzaDAO.getPizzasByPizzeriaId(id);
    }
}
