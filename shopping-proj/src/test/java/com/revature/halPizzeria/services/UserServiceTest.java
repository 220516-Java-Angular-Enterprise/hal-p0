package com.revature.halPizzeria.services;

import com.revature.halPizzeria.daos.UserDAO;
import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest extends TestCase {
    UserService userService = new UserService(new UserDAO());

    public void testWillThrowIf_PasswordEmpty() {
        String username = "halcorn1";
        String password = "";

        Assert.assertThrows(InvalidUserException.class, () -> userService.login(username,password));
    }

    public void testWillThrowIf_NotValidUsername() {
        String username = "halcoro";
        Assert.assertThrows(InvalidUserException.class , () -> userService.isValidUsername(username));
    }

    public void testWillThrowIf_NotValidPassword() {
        String password = "Password";
        Assert.assertThrows(InvalidUserException.class, () -> userService.isValidPassword(password));
    }
}