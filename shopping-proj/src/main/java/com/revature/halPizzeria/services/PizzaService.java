package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzaDAO;
import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidInventoryException;
import com.revature.halPizzeria.util.custom_exceptions.InvalidPizzaException;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;

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

    public boolean pizzaIDIsTwoOrMore(String id){
        if (id.matches("^[a-z]{3}$") || id.matches("^[a-z]{2}$")) return true;
        throw new InvalidPizzaException("ID must be 2 or 3 characters");
    }

    public boolean pizzaPriceIsValidInt(int price){
        String strPrice = String.valueOf(price);
        if (strPrice.matches("^[1-9][0-9]*$"))return true;
        throw new InvalidPizzaException("Price must be greater than 0");
    }







}
