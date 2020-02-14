package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.User;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUser(User user);

    int removeUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    User getUserById(int id);
    
}