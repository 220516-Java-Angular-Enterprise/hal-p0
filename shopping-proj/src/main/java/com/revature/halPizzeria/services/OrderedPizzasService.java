package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.OrderedPizzasDAO;
import com.revature.halPizzeria.models.OrderedPizzas;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;

import java.sql.Struct;
import java.util.List;

public class OrderedPizzasService {
    @Inject
    private final OrderedPizzasDAO orderedPizzasDAO;
    @Inject
    public OrderedPizzasService(OrderedPizzasDAO orderedPizzasDAO) {
        this.orderedPizzasDAO = orderedPizzasDAO;
    }

    public void saveOrder(OrderedPizzas obj){
        orderedPizzasDAO.save(obj);
    }

    public List<OrderedPizzas> getAllOrders(){
        return orderedPizzasDAO.getAll();
    }

    public boolean deleteOrder(String id){
        try {
            orderedPizzasDAO.delete(id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<OrderedPizzas> getByOrderId(String id){
        return orderedPizzasDAO.getCartByUser(id);
    }






}
