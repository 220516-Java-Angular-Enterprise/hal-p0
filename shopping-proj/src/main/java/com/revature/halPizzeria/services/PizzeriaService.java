package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaDAO;
import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class PizzeriaService {
    @Inject
    private final PizzeriaDAO pizzeriaDAO;
    @Inject
    public PizzeriaService(PizzeriaDAO pizzeriaDAO) {

        this.pizzeriaDAO = pizzeriaDAO;
    }
    public void register(Pizzeria pizzeria){
        pizzeriaDAO.save(pizzeria);
    }

    public List<Pizzeria> getAllPizzerias(){
        return pizzeriaDAO.getAll();
    }

    public boolean deletePizzeria(String id){
        try {
            pizzeriaDAO.delete(id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }




}
