package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.services.UserService;
import com.revature.halPizzeria.util.annotations.Inject;

public class MainMenu implements IMenu {
@Inject
    private final User user;
    private final UserService userService;

    public MainMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    @Override
    public void start() {

    }
}
