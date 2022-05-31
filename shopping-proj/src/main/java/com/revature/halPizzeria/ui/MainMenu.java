package com.revature.halPizzeria.ui;

import com.revature.halPizzeria.models.*;
import com.revature.halPizzeria.services.*;
import com.revature.halPizzeria.util.annotations.Inject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
                System.out.println("\n[1] Select Your Pizzeria");
                System.out.println("\n[2] View Order History");
                System.out.println("\n[3] Sign Out");
                System.out.print("\nEnter:");

                switch (scan.nextLine()) {
                    case "1":
                        selectPizzeria();
                        break;
                    case "2":
                        viewOrderHistory();
                        break;
                    case "3":
                        break exit;
                    default:
                        System.out.println("\nInvalid selection");
                        break ;
                }

            }
        }
    }
    /////* Customer Info *//////
    private void viewOrderHistory() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n" + user.getUsername() + " Order History");
        List<PizzaOrders> history = pizzaOrderService.getOrderByUser(user.getId());
        List<Pizzeria> pizzerias = pizzeriaService.getAllPizzerias();


        List<PizzaOrders> lowToHigh = history.stream().sorted(Comparator.comparingInt(PizzaOrders::getOrder_price)).collect(Collectors.toList());
        List<PizzaOrders> highToLow = history.stream().sorted(Comparator.comparingInt(PizzaOrders::getOrder_price)).collect(Collectors.toList());

        lowToHigh.forEach(System.out::println);
        exit:
        {
            while (true) {
                System.out.println("\n[1] Sort Price Low to High");
                System.out.println("\n[2] Sort Price High to Low");
                System.out.println("\nEnter: ");
                switch (scan.nextLine()) {
                    case "1":
                        lowToHigh.forEach(System.out::println);
                        break;
                    case "2":
                        highToLow.forEach(System.out::println);
                        break;
                    default:
                        System.out.println("\nInvalid selection!");
                        break exit;
                }
            }
        }
    }

//        for (int i = 0; i < history.size(); i++){
//            System.out.println("["+(i+1)+"]"+
//                    "\nOrder Date: "+ history.get(i).getOrder_date()+
//                    "\nTotal: $"+history.get(i).getOrder_price()  + "\nPizzeria Info: " + pizzerias.get(i).getCity());
//        }

        //Sort By Cost (High to Low) & (Low to High)



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
            String randomOrderID = UUID.randomUUID().toString();
            viewPizzas(selectedPizzeria,randomOrderID);
        } else System.out.println("\nInvalid restaurant selection!");

    }

    private void viewPizzas(Pizzeria selectedPizzeria, String randomOrderID) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nAvailable pizzas at the " + selectedPizzeria.getCity() + " Pizzeria");
        List<Pizza> pizzas = pizzaService.getPizzasByPizzeria(selectedPizzeria.getId());

        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] " + pizzas.get(i).getPizza_name() + " | " +
                            pizzas.get(i).getPizza_desc() + " | $" + pizzas.get(i).getPrice());
            }


        System.out.println("\nSelect a pizza to add to your cart");
        System.out.println("\n[x] To go back");

        System.out.print("\nEnter: ");
        int input = scan.nextInt()-1;
        scan.nextLine();
        if (input>= 0 && input<pizzas.size()){
            Pizza selectedPizza = pizzas.get(input);
            makeCart(selectedPizza, selectedPizzeria, input, randomOrderID);
        }


    }

    private void makeCart(Pizza selectedPizza, Pizzeria selectedPizzeria, int input, String randomOrderID) {
        Scanner scan = new Scanner(System.in);

        String pizzaID = selectedPizza.getId();
        int quantity;


        exit:{
            while (true){
                System.out.println("\nQuantity of "+ selectedPizza.getPizza_name()+"'s: ");
                quantity = scan.nextInt();
                scan.nextLine();
                List <PizzeriaInventory> pizzeriaInventories = pizzeriaInventoryService.getInventoryByPizzeria(selectedPizzeria.getId());
                int ogInput = pizzeriaInventories.get(input).getQuantity();
                int newInventQuantity = ogInput - quantity;
                OrderedPizzas orderedPizzas = new OrderedPizzas(randomOrderID,pizzaID,quantity);
                orderedPizzasService.saveOrder(orderedPizzas);
                pizzeriaInventoryService.subInventory(selectedPizza.getId(), selectedPizzeria.getId(), newInventQuantity);
                System.out.println("\n[1] View Cart and Checkout");
                System.out.println("\n[2] Add more Pizzas!");
                System.out.print("\nEnter: ");
                switch (scan.nextLine()){
                    case "1":
                        viewCart(orderedPizzas, selectedPizza, selectedPizzeria);
                        break exit;
                    case "2":
                        viewPizzas(selectedPizzeria, randomOrderID);
                        break;
                    default:
                        System.out.println("\n Invalid input");
                        break;
                }

            }
        }


    }


    //*************     FIX THIS TO SO IT SAVES TO DATABASE      **************//

    private void viewCart(OrderedPizzas orderedPizzas, Pizza selectedPizza, Pizzeria selectedPizzeria){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n"+user.getUsername() + " Cart:");
        List<OrderedPizzas> orderPizza = orderedPizzasService.getByOrderId(orderedPizzas.getOrder_id());

        for (int i = 0; i < orderPizza.size(); i++){
            System.out.println("["+(i+1)+"]"+ "\nPizza: "+ selectedPizza.getPizza_name()+
                    "\nQuantity: "+orderPizza.get(i).getPizza_quantity());
        }
        System.out.println("\nWould you like to checkout?");
        System.out.print("\nEnter: (y/n) ");
        switch (scan.nextLine()){
            case "y":
                checkout(orderedPizzas, selectedPizzeria,selectedPizza);
                break;
            case "n":
                viewPizzas(selectedPizzeria, orderedPizzas.getOrder_id());
            default:
                System.out.println("\nInvalid Input!");
        }
    }

    private void checkout(OrderedPizzas orderedPizzas, Pizzeria selectedPizzeria, Pizza selectedPizza){
        Scanner scan = new Scanner(System.in);
        System.out.println("\nConfirm Order:");
        //get date//
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String order_date = formatter.format(date);
        //get total//
        int orderPrice = orderedPizzas.getPizza_quantity() * selectedPizza.getPrice() ;
        //initialize mb//
        PizzaOrders pizzaOrders= new PizzaOrders();
        pizzaOrders.setUser_id(user.getId());
        pizzaOrders.setId(orderedPizzas.getOrder_id());
        pizzaOrders.setPizzeria_id(selectedPizzeria.getId());
        pizzaOrders.setOrder_price(orderPrice);
        pizzaOrders.setOrder_date(order_date);
        pizzaOrderService.saveOrder(pizzaOrders);
        // get order_id from cart, get pizzeria_id, get user id , get
        System.out.println("\nOrder Date: " + pizzaOrders.getOrder_date()+ " | Pizzeria: " + selectedPizzeria.getCity());
        System.out.println("\nOrdered Pizzas: "+ selectedPizza.getPizza_name() +" | Quantity: "+ orderedPizzas.getPizza_quantity());
        System.out.println("\nOrder Total: $" +pizzaOrders.getOrder_price());

        System.out.println("\nConfirm (y/n)");
        System.out.println("\nEnter");
        switch (scan.nextLine()){
            case "y":
                System.out.println("\nHave a Nice Day!!");
                start();
            case "n":
                break;
            default:
                System.out.println("\nInvalid input");
                break;
        }

    }





}
