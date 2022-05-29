package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.services.PizzeriaService;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzeriaDAO implements CrudDAO<Pizzeria>{
    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(Pizzeria obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO pizzerias (id, city, state) VALUES (?,?,?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getCity());
            ps.setString(3,obj.getState());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble saving to database!");
        }

    }

    @Override
    public void update(Pizzeria obj) {

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM pizzerias WHERE id= ?");
            ps.setString(1,id);
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to delete from database!");
        }

    }

    @Override
    public Pizzeria getById(String id) {
        return null;
    }



    @Override
    public List<Pizzeria> getAll() {
        List <Pizzeria> pizzerias = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pizzerias");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                pizzerias.add(new Pizzeria(rs.getString("id"),
                        rs.getString("city"),rs.getString("state") ));
            }

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Pizzerias!");
        }


        return pizzerias;
    }




}
