package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzaDAO;
import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class PizzaService {
    @Inject
    private final PizzaDAO pizzaDAO;
    @Inject
    public PizzaService(PizzaDAO pizzaDAO){

        this.pizzaDAO = pizzaDAO;
    }

    public void register(Pizza pizza){
        pizzaDAO.save(pizza);
    }

    public List<Pizza> getPizzasByPizzeria(String pizzeria_id){

        return pizzaDAO.getPizzasByPizzeriaId(pizzeria_id);
    }

    public boolean deletePizza(String id){
        try{
            pizzaDAO.delete(id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }




}
