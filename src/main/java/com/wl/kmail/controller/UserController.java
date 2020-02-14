package com.wl.kmail.controller;

import com.wl.kmail.model.User;
import com.wl.kmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.POST)
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserById/{id}", method = RequestMethod.POST)
    public boolean deleteUserById(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }
}
