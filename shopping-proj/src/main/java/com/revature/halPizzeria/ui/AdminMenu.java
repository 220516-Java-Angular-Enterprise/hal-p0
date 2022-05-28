package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.services.PizzeriaService;
import com.revature.halPizzeria.services.UserService;
import com.revature.halPizzeria.util.annotations.Inject;

public class AdminMenu implements IMenu{
@Inject
    private final User user;
    private PizzeriaService pizzeriaService;


@Inject
    public AdminMenu(User user, PizzeriaService pizzeriaService) {
        this.user = user;
        this.pizzeriaService = pizzeriaService;
    }

    @Override
    public void start() {

    }
}
