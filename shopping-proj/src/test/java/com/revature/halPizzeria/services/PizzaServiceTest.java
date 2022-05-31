package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzaDAO;
import com.revature.halPizzeria.util.custom_exceptions.InvalidPizzaException;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;
import junit.framework.TestCase;
import org.junit.Assert;

public class PizzaServiceTest extends TestCase {
    PizzaService pizzaService = new PizzaService(new PizzaDAO());
    public void testWillThrowIfMissingPizzaIDWhenDelete(){
        String id = "";
        boolean isTrue = pizzaService.deletePizza(id);
        assertTrue(isTrue);
    }

    public void testWillThrowIfIDNotValid(){
        String id = "LLLL";
        Assert.assertThrows(InvalidPizzaException.class, () -> pizzaService.pizzaIDIsTwoOrMore(id));
    }

    public void testWillThrowIfPriceZeroOrLess(){
        int price = 0;
        Assert.assertThrows(InvalidPizzaException.class, () -> pizzaService.pizzaPriceIsValidInt(price));
    }

}