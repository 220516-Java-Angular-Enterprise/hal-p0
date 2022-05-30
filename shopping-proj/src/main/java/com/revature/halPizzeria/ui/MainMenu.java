package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.*;
import com.revature.halPizzeria.services.*;
import com.revature.halPizzeria.util.annotations.Inject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
@Inject
    private final User user;
    private final UserService userService;
    private final PizzeriaService pizzeriaService;
    private final OrderedPizzasService orderedPizzasService;
    private final PizzaOrderService pizzaOrderService;
    private final PizzaService pizzaService;

    private final PizzeriaInventoryService pizzeriaInventoryService;

    public MainMenu(User user, UserService userService,
                    PizzeriaService pizzeriaService,
                    OrderedPizzasService orderedPizzasService,
                    PizzaOrderService pizzaOrderService,
                    PizzaService pizzaService,
                    PizzeriaInventoryService pizzeriaInventoryService) {
        this.user = user;
        this.userService = userService;
        this.pizzeriaService = pizzeriaService;
        this.orderedPizzasService = orderedPizzasService;
        this.pizzaOrderService = pizzaOrderService;
        this.pizzaService = pizzaService;
        this.pizzeriaInventoryService = pizzeriaInventoryService;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit:{
            while (true) {
                System.out.println("\nWelcome, "+ user.getUsername());
                System.out.println("\n[1]  Select Your Pizzeria");
                System.out.println("\n[2] View Order History");
                System.out.println("\n[3] Edit Customer Profile"); /// MAYBE OR JUST EXIT
                System.out.print("\nEnter:");

                switch (scan.nextLine()) {
                    case "1":
                        selectPizzeria();
                        break;
                    case "2":
                        viewOrderHistory();
                        break;
                    case "3":
                        editUserInfo();
                        break;
                    default:
                        System.out.println("\nInvalid selection");
                        break exit;
                }

            }
        }
    }
    /////* Customer Info *//////
    private void viewOrderHistory(){}
    private void editUserInfo(){}

    /* ORDERING PIZZAS */

    private void selectPizzeria(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nSelect Your Pizzeria, " + user.getUsername());
        List<Pizzeria> pizzerias = pizzeriaService.getAllPizzerias();

        for (int i = 0; i < pizzerias.size(); i++){
            System.out.println("["+(i+1)+"]"+ pizzerias.get(i).getCity());
        }

        System.out.print("\nEnter: ");
        int input = scan.nextInt()-1;
        scan.nextLine();
        if (input >= 0 && input < pizzerias.size()) {
            Pizzeria selectedPizzeria = pizzerias.get(input);
            System.out.print("\n"+ selectedPizzeria.getCity()+", "+ selectedPizzeria.getState());
            viewPizzas(selectedPizzeria);
        } else System.out.println("\nInvalid restaurant selection!");

    }

    private void viewPizzas(Pizzeria selectedPizzeria){
        Scanner scan = new Scanner(System.in);
        String pizzeria_id = selectedPizzeria.getId();

        System.out.println("\nAvailable pizzas at the "+ selectedPizzeria.getCity()+ " Pizzeria");
        List<Pizza> pizzas = pizzaService.getPizzasByPizzeria(selectedPizzeria.getId());
        //System.out.println(pizzas);

        for (int i=0; i< pizzas.size();i++){
            System.out.println("\n["+(i+1)+"] "+ pizzas.get(i).getPizza_name() +" | "+ pizzas.get(i).getPizza_desc()+" | $"+ pizzas.get(i).getPrice());
        }

        System.out.println("\nSelect a pizza to add to your cart");
        System.out.println("\n[x] To go back");

        System.out.print("\nEnter: ");
        int input = scan.nextInt()-1;
        scan.nextLine();
        if (input>= 0 && input<pizzas.size()){
            Pizza selectedPizza = pizzas.get(input);
            makeCart(selectedPizza, selectedPizzeria, input);
        }

    }

    private void makeCart(Pizza selectedPizza, Pizzeria selectedPizzeria, int input){
        Scanner scan = new Scanner(System.in);
        OrderedPizzas orderedPizzas = new OrderedPizzas();

        exit:{
            while (true){
                System.out.println("\nQuantity of "+ selectedPizza.getPizza_name()+"'s: ");
                int quantity = scan.nextInt();
                scan.nextLine();
                List <PizzeriaInventory> pizzeriaInventories = pizzeriaInventoryService.getInventoryByPizzeria(selectedPizzeria.getId());

                int ogInput = pizzeriaInventories.get(input).getQuantity();
                int newInventQuantity = ogInput - quantity;

                orderedPizzas.setOrder_id(UUID.randomUUID().toString());
                orderedPizzas.setPizza_quantity(quantity);
                orderedPizzas.setPizza_id(selectedPizza.getId());
                pizzeriaInventoryService.subInventory(selectedPizza.getId(), selectedPizzeria.getId(), newInventQuantity);
                orderedPizzasService.saveOrder(orderedPizzas);
                System.out.println("\n[1] View Cart and Checkout");
                System.out.println("\n[2] Add more Pizzas!");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()){
                    case "1":

                        checkout(selectedPizzeria, orderedPizzas, selectedPizza);
                        break exit;
                    case "2":
                        viewPizzas(selectedPizzeria);
                        break;
                    default:
                        System.out.println("\n Invalid input");
                        break;
                }

            }
        }
    }

    private void checkout(Pizzeria selectedPizzeria, OrderedPizzas orderedPizzas, Pizza selectedPizza){
        Scanner scan = new Scanner(System.in);
        PizzaOrders pizzaOrders = new PizzaOrders();
        pizzaOrders.setId(UUID.randomUUID().toString());
        pizzaOrders.setPizzeria_id(selectedPizzeria.getId());
        pizzaOrders.setUser_id(user.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        pizzaOrders.setOrder_date(String.valueOf(date));
        pizzaOrders.setOrder_price(12);

        System.out.println(pizzaOrders);

        // do math to get order total price (get pizza_id prize)*(get quantity by pizza id) + yeah
        //

    }



}
