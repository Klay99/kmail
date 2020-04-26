package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.Friend;

import java.util.List;

@Mapper
public interface FriendDao {

    List<Friend> getAllFriend(Friend friend);

    int removeFriendById(int id);

    int addFriend(Friend friend);

    int updateFriend(Friend friend);

    Friend getFriendById(int id);
    
}