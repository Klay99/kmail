package com.wl.kmail.service;

import com.wl.kmail.dao.UserDao;
import com.wl.kmail.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public boolean addUser(User user) {
        userDao.addUser(user);
        return userDao.getUserById(user.getId()) != null;
    }

    @Override
    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id) == 1;
    }

    @Override
    public User updateUser(User user) {
        userDao.updateUser(user);
        return userDao.getUserById(user.getId());
    }
}
