package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.util.custom_exceptions.InvalidSQLException;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO implements CrudDAO<Pizza> {

    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(Pizza obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO pizzas (id, pizza_name, price, pizza_desc) VALUES (?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getPizza_name());
            ps.setString(3, String.valueOf(obj.getPrice()));
            ps.setString(4, obj.getPizza_desc());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble saving to database!");
        }

    }

    @Override
    public void update(Pizza obj) {

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM pizzas where id = ?");
            ps.setString(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InvalidSQLException("Error! Trouble trying to delete from database!");
        }

    }

    @Override
    public Pizza getById(String id) {
        return null;
    }


    @Override
    public List<Pizza> getAll() {
        List<Pizza> pizzas = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pizzas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){


            }
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Pizzas!");
        }


        return pizzas;
    }


    public List<Pizza> getPizzasByPizzeriaId(String pizzeria_id ){
        List<Pizza> pizzas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pizzas WHERE pizzeria_id = (?)");
            ps.setString(1,pizzeria_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                pizzas.add(new Pizza(rs.getString("id"), rs.getString("pizza_name"),
                        rs.getInt("price"), rs.getString("pizza_desc")));

            }
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Pizzas!");
        }
        return pizzas;
    }
}
