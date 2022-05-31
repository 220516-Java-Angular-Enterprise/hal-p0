package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.PizzeriaDAO;
import com.revature.halPizzeria.util.custom_exceptions.InvalidInventoryException;
import com.revature.halPizzeria.util.custom_exceptions.InvalidPizzeriaException;
import junit.framework.TestCase;
import org.junit.Assert;

public class PizzeriaServiceTest extends TestCase {
    PizzeriaService pizzeriaService = new PizzeriaService(new PizzeriaDAO());

    public void testIfIAmNOTTexan_WIllThrow() {
        String state = "Nebraska";
        Assert.assertThrows(InvalidPizzeriaException.class, () -> pizzeriaService.iAmTexan(state));
    }
}