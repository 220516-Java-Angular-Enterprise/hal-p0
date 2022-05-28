package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.daos.PizzeriaDAO;
import com.revature.halPizzeria.daos.UserDAO;
import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.services.PizzeriaService;
import com.revature.halPizzeria.services.UserService;
import com.revature.halPizzeria.util.annotations.Inject;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;

import java.util.Scanner;
import java.util.UUID;

public class StartMenu implements IMenu {

    @Inject
    private final UserService userService;

    @Inject
    public StartMenu(UserService userService){
        this.userService =userService;
    }
    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:{
            while(true){
                displayWelcomeMenu();

                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch(input){
                    case "1":
                        login();
                        break;
                    case "2":
                        signup();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("Invalid Input.");
                        break;
                }
            }
        }
    }

    private void displayWelcomeMenu(){
        System.out.println("\nWelcome to Hal's Pizzeria!!");
        System.out.println("[1] Login");
        System.out.println("[2] Sign Up");
        System.out.println("[x] Exit");
    }
    private void login(){
        String username;
        String password;
        User user = new User();
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("\nPlease Log In:");
            System.out.println("\nUsername: ");
            username = scan.nextLine();

            System.out.println("\nPassword: ");
            password = scan.nextLine();

            try{
                user =userService.login(username,password);
                if (user.getRole().equals("ADMIN")){
                    new AdminMenu(user, new PizzeriaService(new PizzeriaDAO())).start();
                }else {
                    new MainMenu(user, new UserService(new UserDAO())).start();
                break;}
            } catch (InvalidUserException e){
                System.out.println(e.getMessage());
            }

        }

    }


    /////********* SIGN UP ************///////
    private void signup(){
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("\nCreate Account!");

            completeExit:{
                while (true){
                System.out.print("\nUsername: ");
                username = scan.nextLine();

                try{
                    if (userService.isValidUsername(username)) {
                        System.out.println("Username Accepted!");
                        // add if dupe///
                        break;
                    }
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }

                while (true) {
                    while (true) {
                        System.out.print("\nPassword ");
                        password = scan.nextLine();

                        try{
                            if (userService.isValidPassword(password)) {
                                System.out.print("\nRe-enter Password to verify: ");
                                String confirm = scan.nextLine();

                                if (password.equals(confirm)) break;
                                else System.out.println("Password does not match");
                            }
                        } catch (InvalidUserException e){
                            System.out.println(e.getMessage());
                        }


                    }
                    confirmExit:
                    {
                        while(true){
                            System.out.println("\nPlease confirm your credentials are correct!");
                            System.out.println("Username: " + username);
                            System.out.println("Password: " + password);
                            System.out.println("Enter: (y/n)");
                            String input = scan.nextLine();

                            switch (input){
                                case "y":
                                    User user =new User(UUID.randomUUID().toString(),username,password, "DEFAULT");
                                    userService.register(user);

                                    new MainMenu(user, new UserService(new UserDAO())).start(); ///add in other stuff
                                    break completeExit;
                                case "n":
                                    break confirmExit;
                                default:
                                    System.out.println("Invalid input!");
                                    break;
                            }
                        }
                    }
                }

        }

        }




    }
}
