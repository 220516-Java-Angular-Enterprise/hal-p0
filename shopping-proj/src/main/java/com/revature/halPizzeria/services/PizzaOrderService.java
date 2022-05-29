package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzaOrderDAO;
import com.revature.halPizzeria.models.PizzaOrders;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class PizzaOrderService {
    @Inject
    private final PizzaOrderDAO pizzaOrderDAO;
    @Inject
    public PizzaOrderService(PizzaOrderDAO pizzaOrderDAO) {
        this.pizzaOrderDAO = pizzaOrderDAO;
    }

    public void saveOrder(PizzaOrders pizzaOrders){
        pizzaOrderDAO.save(pizzaOrders);
    }

    public boolean deleteOrder(String id){
        try {
            pizzaOrderDAO.delete(id);
            return true;
        }catch (InvalidSQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<PizzaOrders> getOrderByUser(PizzaOrders obj){
        return pizzaOrderDAO.getPizzaOrdersByUser(obj);
    }


}
