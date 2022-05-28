package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaDAO;
import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.util.annotations.Inject;

import java.util.List;

public class PizzeriaService {
    @Inject
    private final PizzeriaDAO pizzeriaDAO;
    @Inject
    public PizzeriaService(PizzeriaDAO pizzeriaDAO) {
        this.pizzeriaDAO = pizzeriaDAO;
    }




    public List<Pizzeria> getAllPizza(){
        return pizzeriaDAO.getAll();
    }


}
