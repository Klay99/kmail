package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.User;

public interface UserService {

	Object getAllUser(PageParam<User> pageParam);

    boolean removeUserById(int id);

    Object addUser(User user);

    boolean updateUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);

    User login(User user);
}