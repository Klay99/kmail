package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Friend;

public interface FriendService {

	Object getAllFriend(PageParam<Friend> pageParam);

    boolean removeFriendById(int id);

    Object addFriend(Friend friend);

    boolean updateFriend(Friend friend);

    Friend getFriendById(int id);

}