package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaInventoryDAO;
import com.revature.halPizzeria.models.OrderedPizzas;
import com.revature.halPizzeria.models.PizzeriaInventory;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidInventoryException;
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

    public boolean addInventory(int quantity, String pizza_id, String pizzeria_id){
        try{
            pizzeriaInventoryDAO.updateQuantity(quantity,pizza_id,pizzeria_id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean subInventory(String pizza_id, String pizzeria_id,int obj){
        try{
            pizzeriaInventoryDAO.subQuantity(pizza_id,pizzeria_id,obj);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean inventoryIsGreaterThanZero(int quantity){
        String quant = String.valueOf(quantity);
        if (quant.matches("^[1-9][0-9]*$")) return true;
        throw new InvalidInventoryException("Quantity must be greater than 0!");
    }

}
