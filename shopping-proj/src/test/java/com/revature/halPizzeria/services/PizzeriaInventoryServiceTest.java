package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaInventoryDAO;
import com.revature.halPizzeria.util.custom_exceptions.InvalidInventoryException;
import junit.framework.TestCase;
import org.junit.Assert;

public class PizzeriaInventoryServiceTest extends TestCase {
    PizzeriaInventoryService pizzeriaInventoryService = new PizzeriaInventoryService(new PizzeriaInventoryDAO());

    public void testDeleteInventoryByPizzeria_WillThrowIfPizzeriaNotValid() {
        String pizzeria_id = "56d1ef66-acac-4e21-a0f4-ade640d41ad3";

        boolean isTrue = pizzeriaInventoryService.deletePizzeriaInvent(pizzeria_id);

        assertTrue(isTrue);

    }

    public void test_WillThrowExceptionIfInventoryIsZeroOrLess(){
        int quantity = -1;
        Assert.assertThrows(InvalidInventoryException.class, () -> pizzeriaInventoryService.inventoryIsGreaterThanZero(quantity));
    }

    public void testAddInventory_WillThrowIfPizzeriaInvalid(){
        int quantity = 12;
        String pizza = "CP";
        String pizzeria = "52749400-dccd-480f-ba1e-4be30de57f4d";

        boolean isTrue = pizzeriaInventoryService.addInventory(quantity,pizza,pizzeria);

        assertTrue(isTrue);
    }

}