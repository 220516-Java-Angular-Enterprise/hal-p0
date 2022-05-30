package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.OrderedPizzas;
import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.models.PizzeriaInventory;
import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzeriaInventoryDAO implements CrudDAO<PizzeriaInventory> {
    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(PizzeriaInventory obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventory (pizzeria_id, pizza_id, quantity) VALUES(?,?,?)");
            ps.setString(1,obj.getPizzeria_id());
            ps.setString(2,obj.getPizza_id());
            ps.setInt(3,obj.getQuantity());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble saving to database!");
        }
    }

    @Override
    public void update(PizzeriaInventory obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM inventory WHERE pizzeria_id = (?)");
            ps.setString(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to delete from database!");
        }

    }

    @Override
    public PizzeriaInventory getById(String id) {
        return null;
    }

    @Override
    public List<PizzeriaInventory> getAll() {
        return null;
    }

    public List<PizzeriaInventory> getAllByPizzeriaID(String pizzeria_id){
        List<PizzeriaInventory> pizzeriaInventories = new ArrayList<>();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory WHERE pizzeria_id = (?) ");
                ps.setString(1,pizzeria_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    pizzeriaInventories.add(new PizzeriaInventory(rs.getString("pizzeria_id"),
                            rs.getString("pizza_id"), rs.getInt("quantity")));
                }


            }catch (SQLException e){
                throw new RuntimeException("Error! Trouble trying to retrieve Pizzerias Inventory!");
            }

    return pizzeriaInventories;}

    public void updateQuantity(int quantity, String pizza_id, String pizzeria_id){
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE inventory SET quantity = (?) WHERE pizza_id = (?) AND pizzeria_id = (?)");
            ps.setInt(1,quantity);
            ps.setString(2,pizza_id);
            ps.setString(3,pizzeria_id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InvalidUserException("Error! Trouble trying to add to stock");
        }
    }

    public void subQuantity(String pizza_id, String pizzeria_id, int quantity){
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE inventory SET quantity = (?) WHERE pizza_id = (?) AND pizzeria_id = (?)");
            ps.setInt(1,quantity);
            ps.setString(2,pizza_id);
            ps.setString(3,pizzeria_id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InvalidUserException("Error! Trouble trying to add to stock");
        }
    }




}
