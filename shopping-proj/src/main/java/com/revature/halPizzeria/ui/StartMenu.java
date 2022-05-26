package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.services.UserService;

import java.util.Scanner;
import java.util.UUID;

public class StartMenu implements IMenu {
    private final UserService userService;
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
        System.out.println("Add in later... ");
    }


    /////********* SIGN UP ************///////
    private void signup(){
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreate Account!");
        while (true){
            System.out.print("\nUsername: ");
            username = scan.nextLine();

            if (userService.isValidUsername(username)) {
                System.out.println("Username Accepted!");
                break;
            }else System.out.println("Invalid Username:Must be 8-20 characters long");
        }

        while (true) {
            while (true) {
                System.out.print("\nPassword ");
                password = scan.nextLine();

                if (userService.isValidPassword(password)) {
                    System.out.print("Re-enter Password to verify: ");
                    String confirm = scan.nextLine();

                    if (password.equals(confirm)) break;
                    else System.out.println("Password does not match");
                }
                else System.out.println("Invalid Password! Minimum eight characters, at least one letter, one number and one special character:");

            }
            confirmExit:
            {
                while(true){
                    System.out.println("\nPlease confirm your credentials are correct!");
                    System.out.println("Username:" + username);
                    System.out.println("Password" + password);
                    System.out.println("Enter: (y/n)");
                    String input = scan.nextLine();

                    switch (input){
                        case "y":
                            User user =new User(UUID.randomUUID().toString(),username,password, "DEFAULT");
                            System.out.println(user);

                            new MainMenu(user).start();
                            break confirmExit;
                        case "n":
                            break;
                        default:
                            System.out.println("Invalid input!");
                            break;
                    }
                }
            }


        }

    }
}
