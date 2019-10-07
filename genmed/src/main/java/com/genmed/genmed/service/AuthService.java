package com.genmed.genmed.service;

import com.genmed.genmed.model.Shop;
import com.genmed.genmed.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User userLogin(String username, String password) { return null; };
    public Shop shopLogin(String username, String password, String shopPin) { return null; };

    public void logout() {};

    public User userRegister() { return null; };
    public Shop shopRegister() { return null; };

}
