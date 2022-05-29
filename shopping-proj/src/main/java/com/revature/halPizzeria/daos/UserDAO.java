package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role) VALUES(?, ?, ?, ?)");
            ps.setString(1,obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3,obj.getPassword());
            ps.setString(4, obj.getRole());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble saving to database!");
        }

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id= ?");
            ps.setString(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to delete from database!");
        }

    }

    @Override
    public User getById(String id) {
        return null;
    }


    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                usernames.add(rs.getString("username"));
            }
        }catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Usernames!");
        }
        return usernames;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                users.add(user);
            }

            } catch (SQLException e){
            throw new RuntimeException("Error! Trouble trying to retrieve Users!");}

        return users;
    }
}
