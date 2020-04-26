package com.wl.kmail.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.wl.kmail.config.exception.HttpCode;
import com.wl.kmail.config.exception.MyException;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.dao.FriendDao;
import com.wl.kmail.model.Friend;

import java.util.List;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
    FriendDao friendDao;

	@Override
	public Object getAllFriend(PageParam<Friend> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }

        List<Friend> friendList=friendDao.getAllFriend(pageParam.getModel());
        PageInfo<Friend> friendPageInfo = new PageInfo<Friend>(friendList);

        return friendPageInfo;
        
    }

    @Override
    public boolean removeFriendById(int id){
    	return friendDao.removeFriendById(id)==1;
    }

    @Override
    public Object addFriend(Friend friend){
        friendDao.addFriend(friend);

        return friendDao.getFriendById(friend.getId());
    }

	@Override
    public boolean updateFriend(Friend friend){
    	if(StringUtils.isEmpty(friend.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改friend时，id不能为空");
        }

        return friendDao.updateFriend(friend)==1;
    }

    @Override
    public Friend getFriendById(int id){
    	return friendDao.getFriendById(id);
    	
    }

}
