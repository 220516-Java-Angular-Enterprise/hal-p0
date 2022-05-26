package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.User;

public class MainMenu implements IMenu {

    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {

    }
}
