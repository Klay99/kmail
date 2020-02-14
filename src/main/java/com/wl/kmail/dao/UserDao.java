package com.wl.kmail.dao;

import com.wl.kmail.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUser();

    User getUserById(int id);

//    @Insert("insert into user(username,password,email,headImg,isNew) values('koty','123','123','132',3)")
    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);
}
