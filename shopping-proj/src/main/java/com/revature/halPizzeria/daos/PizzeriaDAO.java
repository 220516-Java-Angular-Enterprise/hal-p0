package com.revature.halPizzeria.daos;

import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.services.PizzeriaService;
import com.revature.halPizzeria.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class PizzeriaDAO implements CrudDAO<Pizzeria>{
    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(Pizzeria obj) {

    }

    @Override
    public void update(Pizzeria obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Pizzeria getById(String id) {
        return null;
    }



    @Override
    public List<Pizzeria> getAll() {
        return null;
    }
}
