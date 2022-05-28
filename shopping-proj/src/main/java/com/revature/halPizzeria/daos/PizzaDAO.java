package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.Pizza;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class PizzaDAO implements CrudDAO<Pizza> {

    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(Pizza obj) {

    }

    @Override
    public void update(Pizza obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Pizza getById(String id) {
        return null;
    }


    @Override
    public List<Pizza> getAll() {
        return null;
    }
}
