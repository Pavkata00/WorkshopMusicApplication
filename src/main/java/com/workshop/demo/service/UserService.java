package com.workshop.demo.service;

import com.workshop.demo.model.service.UserServiceModel;

public interface UserService {
    void seedUsers();

    void registerAndLogin(UserServiceModel userServiceModel);

    boolean usernameExists(String username);
}
