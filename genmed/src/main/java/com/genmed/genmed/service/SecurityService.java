package com.genmed.genmed.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
