package com.revature.halPizzeria.services;

import com.revature.halPizzeria.models.User;
import com.revature.halPizzeria.util.custom_exceptions.InvalidUserException;

import java.util.StringTokenizer;

public class UserService {
//    @Inject
//    private final UserDAO userDAO;
//
//    @Inject
//    public UserService(UserDAO userDAO) {
//        this.userDAO = userDAO;
    public User login(String username, String password) {
        /* List<User> users = new ArrayList<>() */
        /* users = userDAO.getAll() */

        User user = new User();
        List<User> users = userDAO.getAll();

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                user.setRole(u.getRole());
                if (u.getPassword().equals(password)) {
                    user.setPassword(u.getPassword());
                    break;
                }
            }
            if (u.getPassword().equals(password)) {
                user.setPassword(u.getPassword());
            }
        }

        return isValidCredentials(user);
    }







    public boolean isValidUsername(String username){
        if(username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;

        throw new InvalidUserException("Invalid Username:Must be 8-20 characters long");

    }

    public boolean isValidPassword(String password){
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")){return true;}

        throw new InvalidUserException("Invalid Password! Minimum eight characters, at least one letter, one number and one special character:");
    }
}