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
import com.wl.kmail.dao.UserDao;
import com.wl.kmail.model.User;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserDao userDao;

	@Override
	public Object getAllUser(PageParam<User> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }

        List<User> userList=userDao.getAllUser(pageParam.getModel());
        PageInfo<User> userPageInfo = new PageInfo<User>(userList);

        return userPageInfo;
        
    }

    @Override
    public boolean removeUserById(int id){
    	return userDao.removeUserById(id)==1;
    }

    @Override
    public Object addUser(User user){
        userDao.addUser(user);

        return userDao.getUserById(user.getId());
    }

	@Override
    public boolean updateUser(User user){
    	if(StringUtils.isEmpty(user.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改user时，id不能为空");
        }

        return userDao.updateUser(user)==1;
    }

    @Override
    public User getUserById(int id){
    	return userDao.getUserById(id);
    	
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

}
