package com.revature.halPizzeria;

import com.revature.halPizzeria.services.UserService;
import com.revature.halPizzeria.ui.StartMenu;
import com.revature.halPizzeria.util.database.DatabaseConnection;

public class MainDriver {
    public static void main(String[] args){
//        UserService userService = new UserService();
//
//        new StartMenu(userService).start();
        System.out.println(DatabaseConnection.getCon());
    }
}
