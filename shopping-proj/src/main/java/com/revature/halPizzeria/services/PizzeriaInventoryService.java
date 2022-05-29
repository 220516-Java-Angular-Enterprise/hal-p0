package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaInventoryDAO;
import com.revature.halPizzeria.models.PizzeriaInventory;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class PizzeriaInventoryService {
    @Inject
    private final PizzeriaInventoryDAO pizzeriaInventoryDAO;
    @Inject
    public PizzeriaInventoryService(PizzeriaInventoryDAO pizzeriaInventoryDAO) {
        this.pizzeriaInventoryDAO = pizzeriaInventoryDAO;
    }

    public List<PizzeriaInventory> getInventoryByPizzeria(String pizzeria_id){
        return pizzeriaInventoryDAO.getAllByPizzeriaID(pizzeria_id);
    }

    public void saveInvent(PizzeriaInventory inventory){
        pizzeriaInventoryDAO.save(inventory);
    }

    public boolean deletePizzeriaInvent(String id){
        try {
            pizzeriaInventoryDAO.delete(id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}
