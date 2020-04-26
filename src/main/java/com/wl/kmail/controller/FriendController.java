package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Friend;
import com.wl.kmail.service.FriendService;

import javax.validation.Valid;

@Api(value = "friend模块接口",description = "这是一个通讯录好友模块的接口文档")
@RestController
@Slf4j
@RequestMapping("friend")
@CrossOrigin
public class FriendController {

	@Autowired
    FriendService friendService;

	@ApiOperation("查询所有通讯录好友 支持多条件分页排序查询")
    @PostMapping("/getAllFriend")
    public Object getAllFriend(@RequestBody PageParam<Friend> pageParam){
        return MyResponse.success(friendService.getAllFriend(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeFriendById/{id}")
    public Object removeFriendByFriendName(@PathVariable("id") int id){
        return friendService.removeFriendById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addFriend")
    public Object addFriend(@RequestBody @Valid Friend friendParam){
        Friend friend=(Friend)friendService.addFriend(friendParam);
        return friend!=null?MyResponse.success(friend).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateFriend")
    public Object updateFriend(@RequestBody@Valid Friend friend){
        return friendService.updateFriend(friend)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getFriendById/{id}")
    public Object getFriendById(@PathVariable("id") int id){
        Friend friend=friendService.getFriendById(id);
        return friend!=null?MyResponse.success(friend):null;
    }
	
}