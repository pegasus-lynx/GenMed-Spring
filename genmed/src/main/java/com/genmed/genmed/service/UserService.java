package com.genmed.genmed.service;

import com.genmed.genmed.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
