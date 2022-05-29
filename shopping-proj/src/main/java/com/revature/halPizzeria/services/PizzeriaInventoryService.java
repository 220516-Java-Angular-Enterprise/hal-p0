package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaInventoryDAO;
import com.revature.halPizzeria.util.annotations.Inject;

public class PizzeriaInventoryService {
    @Inject
    private final PizzeriaInventoryDAO pizzeriaInventoryDAO;
    @Inject
    public PizzeriaInventoryService(PizzeriaInventoryDAO pizzeriaInventoryDAO) {
        this.pizzeriaInventoryDAO = pizzeriaInventoryDAO;
    }



}
