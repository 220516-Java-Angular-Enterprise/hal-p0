package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.OrderedPizzas;
import com.revature.halPizzeria.models.PizzaOrders;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaOrderDAO implements CrudDAO<PizzaOrders>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(PizzaOrders obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO pizza_orders (order_id, user_id, pizzeria_id, order_date, price) VALUES(?,?,?,?,?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getUser_id());
            ps.setString(3,obj.getPizzeria_id());
            ps.setString(4,obj.getOrder_date());
            ps.setInt(5,obj.getOrder_price());
            ps.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());;
        }

    }

    @Override
    public void update(PizzaOrders obj) {

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM pizza_orders WHERE id= ?");
            ps.setString(1,id);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to delete from database!");
        }
    }

    @Override
    public PizzaOrders getById(String id) {
        return null;
    }

    @Override
    public List<PizzaOrders> getAll() {
        return  null;
    }

    public List<PizzaOrders> getPizzaOrdersByUser(String user_id){
        List<PizzaOrders> pizzaOrders = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pizza_orders WHERE user_id = (?)");
            ps.setString(1,user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                pizzaOrders.add(new PizzaOrders(rs.getString("order_id"),rs.getString("user_id"),
                        rs.getString("pizzeria_id"), rs.getString("order_date"),
                        rs.getInt("price")));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Order!");}


        return pizzaOrders;
    }
    public List<PizzaOrders> getPizzaOrdersByPizzeria(String pizzeria_id){
        List<PizzaOrders> pizzaOrders = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pizza_orders WHERE pizzeria_id = (?)");
            ps.setString(1, pizzeria_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                pizzaOrders.add(new PizzaOrders(rs.getString("order_id"),rs.getString("user_id"),
                        rs.getString("pizzeria_id"), rs.getString("order_date"),
                        rs.getInt("price")));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Orders!");}


        return pizzaOrders;
    }

}
