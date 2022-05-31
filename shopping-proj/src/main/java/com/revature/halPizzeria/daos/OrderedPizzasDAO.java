package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.OrderedPizzas;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedPizzasDAO implements CrudDAO<OrderedPizzas>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(OrderedPizzas obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ordered_pizzas (id, pizza_id, order_quantity) VALUES(?,?,?)");
            ps.setString(1,obj.getOrder_id());
            ps.setString(2,obj.getPizza_id());
            ps.setInt(3,obj.getPizza_quantity());
            ps.executeUpdate();

        }catch (SQLException e){

            //System.out.println(e.getMessage());
        throw new RuntimeException("Error! Trouble saving to database!");
        }

    }

    @Override
    public void update(OrderedPizzas obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM ordered_pizzas WHERE order_id = (?)");
            ps.setString(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to delete from database!");
        }


    }

    @Override
    public OrderedPizzas getById(String id) {
        return null;
    }

    @Override
    public List<OrderedPizzas> getAll() {
        List<OrderedPizzas> orderedPizzas = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordered_pizzas");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                orderedPizzas.add(new OrderedPizzas(rs.getString("id"),
                        rs.getString("pizza_id"),rs.getInt("order_quantity")));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Orders!");
        }
        return orderedPizzas;
    }

    public List<OrderedPizzas> getCartByUser(String order_id){
        List <OrderedPizzas> orderedPizzas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordered_pizzas WHERE id = (?)");
            ps.setString(1, order_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                orderedPizzas.add(new OrderedPizzas(rs.getString("id"),
                        rs.getString("pizza_id"),rs.getInt("order_quantity")));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Orders!");
        }
        return orderedPizzas;
    }

}
