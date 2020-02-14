package com.wl.kmail.service;

import com.wl.kmail.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserById(int id);

    boolean addUser(User user);

    boolean deleteUserById(int id);

    User updateUser(User user);
}
