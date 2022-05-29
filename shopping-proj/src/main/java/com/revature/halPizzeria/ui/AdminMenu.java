package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.PizzaOrders;
import com.revature.halPizzeria.models.Pizzeria;
import com.revature.halPizzeria.models.PizzeriaInventory;
import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.services.PizzaOrderService;
import com.revature.halPizzeria.services.PizzeriaInventoryService;
import com.revature.halPizzeria.services.PizzeriaService;
import com.revature.halPizzeria.util.annotations.Inject;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminMenu implements IMenu{
@Inject
    private final User user;
    private final PizzeriaService pizzeriaService;
    private final PizzeriaInventoryService pizzeriaInventoryService;
    private final PizzaOrderService pizzaOrderService;

    @Inject
    public AdminMenu(User user, PizzeriaService pizzeriaService, PizzeriaInventoryService pizzeriaInventoryService, PizzaOrderService pizzaOrderService) {
        this.user = user;
        this.pizzeriaService = pizzeriaService;
        this.pizzeriaInventoryService = pizzeriaInventoryService;
        this.pizzaOrderService = pizzaOrderService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit:{
            while (true) {
                System.out.println("\nWelcome, "+ user.getUsername());
                System.out.println("\n[1]  Select Your Pizzeria");
                System.out.println("\n[2] Add Your Pizzerias");
                System.out.print("\nEnter:");

                switch (scan.nextLine()) {
                    case "1":
                        selectPizzeria();
                        break;
                    case "2":
                        createPizzeria();
                        break;
                    case "3":
                        break exit;
                    default:

                }

            }
        }
    }
/* Create Pizzeria */
        private void createPizzeria(){
            Scanner scan = new Scanner(System.in);
            Pizzeria pizzeria = new Pizzeria();

            restart:
            {
                while (true){
                    pizzeria.setId(UUID.randomUUID().toString());

                    System.out.print("City");
                    pizzeria.setCity(scan.nextLine());

                    System.out.print("State");
                    pizzeria.setState(scan.nextLine());

                    while(true){
                        System.out.println("\nIs the information correct? (y/n)");
                        System.out.println("\n"+ pizzeria);

                        switch (scan.nextLine()){
                            case "y":
                                pizzeriaService.register(pizzeria);
                                break;
                            case "n":
                                break restart;
                            default:
                                System.out.println("\nInvalid Input!");
                                break;
                        }
                    }
                }
            }
            start();
        }
/* Start menu pt. 2*/
        private void selectPizzeria(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nSelect Your Pizzeria, " + user.getUsername());
        List <Pizzeria> pizzerias = pizzeriaService.getAllPizzerias();

        for (int i = 0; i < pizzerias.size(); i++){
            System.out.println("["+(i+1)+"]"+ pizzerias.get(i).getCity());
            }

            System.out.print("\nEnter: ");
            int input = scan.nextInt()-1;
            scan.nextLine();
            if (input >= 0 && input < pizzerias.size()) {
                Pizzeria selectedPizzeria = pizzerias.get(input);
                System.out.print("\n"+ selectedPizzeria.getCity()+","+ selectedPizzeria.getState());
                pizzeriaMenu(selectedPizzeria);
            } else System.out.println("\nInvalid restaurant selection!");

        }

        private void pizzeriaMenu(Pizzeria selectedPizzeria){
            Scanner scan = new Scanner(System.in);
            while(true){
                System.out.println("\nWelcome to "+ selectedPizzeria.getCity() + " Pizzeria Menu");
                System.out.println("\n[1] View Inventory");
                System.out.println("\n[2] View Customer Orders");

                System.out.println("\nEnter: ");

                switch (scan.nextLine()){
                    case "1":
                        viewPizzeriaInventory(selectedPizzeria);
                        break;
                    case "2":
                        viewOrdersByCust(selectedPizzeria);
                        break;
                    default:
                        System.out.println("\nInvalid selection!");
                        break;

                }
            }
        }

        private void viewPizzeriaInventory(Pizzeria selectedPizzeria){
            Scanner scan = new Scanner(System.in);

            System.out.println("Inventory for the" + selectedPizzeria.getCity()+ " Pizzeria");
            List <PizzeriaInventory> pizzeriaInventories = pizzeriaInventoryService.getInventoryByPizzeria(selectedPizzeria.getId());

            for (int i = 0; i < pizzeriaInventories.size(); i++){
                System.out.println("["+(i+1)+"]"+ "\nPizza: "+ pizzeriaInventories.get(i).getPizza_id()+"\nAmount In Stock: "+pizzeriaInventories.get(i).getQuantity());
            }

            System.out.println("\nSelect Pizza to edit Inventory");
            System.out.println("\n[x] To go back to go back to Pizzeria Menu");

            System.out.print("\nEnter: ");
            int input = scan.nextInt()-1;
            scan.nextLine();
            if (input >= 0 && input < pizzeriaInventories.size()) {
                PizzeriaInventory selectedPizza = pizzeriaInventories.get(input);

                System.out.print("\nNew Quantity for"+selectedPizza.getPizza_id()+": ");
                int quantity = scan.nextInt();
                scan.nextLine();
                if (pizzeriaInventoryService.addInventory(quantity,selectedPizza.getPizza_id(),selectedPizza.getPizzeria_id())) {
                    System.out.println("\nInventory was updated!");
                }
            } else pizzeriaMenu(selectedPizzeria);
        }

        private void viewOrdersByCust(Pizzeria selectedPizzeria){
            Scanner scan = new Scanner(System.in);
            System.out.println("Inventory for the" + selectedPizzeria.getCity()+ " Pizzeria");

            List <PizzaOrders> pizzaOrders = pizzaOrderService.getOrderbyPizzeria(selectedPizzeria.getId());
            for (int i = 0; i < pizzaOrders.size(); i++){
                System.out.println("["+(i+1)+"]"+ "\nCustomer ID"+ pizzaOrders.get(i).getUser_id()+
                        "\nOrder Date: "+ pizzaOrders.get(i).getOrder_date()+
                        "\nTotal: $"+pizzaOrders.get(i).getOrder_price() );
            }

            System.out.println("\n[x] Exit to Pizzeria Menu");
            switch (scan.nextLine()){
                case "x":
                    pizzeriaMenu(selectedPizzeria);
                    break;
                default:
                    break;
            }


        }





        }






