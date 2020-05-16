package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.User;
import com.wl.kmail.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Api(value = "user模块接口",description = "这是一个用户模块的接口文档")
@RestController
@Slf4j
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Value("${app.filePath}")
    private String filePath;

	@Autowired
    UserService userService;

	@ApiOperation("查询所有用户 支持多条件分页排序查询")
    @PostMapping("/getAllUser")
    public Object getAllUser(@RequestBody PageParam<User> pageParam){
        return MyResponse.success(userService.getAllUser(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeUserById/{id}")
    public Object removeUserByUserName(@PathVariable("id") int id){
        return userService.removeUserById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody @Valid User userParam){

        User user=(User)userService.addUser(userParam);
        return user!=null?MyResponse.success(user).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateUser")
    public Object updateUser(@RequestBody@Valid User user){
        return userService.updateUser(user)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @PostMapping("/updateHeadImg")
    public Object addImage(@RequestParam("id") int id, @RequestParam(name = "headImg", required = false) MultipartFile file) {
	    User user = userService.getUserById(id);
        if (!file.isEmpty()) {
            try {
                String path = filePath + "img/" + file.getOriginalFilename();
                File newFile = new File(path);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                user.setHeadImg("http://localhost:8100/static/img/" + file.getOriginalFilename());
                userService.updateUser(user);
            } catch (IOException e) {
                e.printStackTrace();
                return MyResponse.error().msg("上传失败");
            }
        }
        return MyResponse.success(user).msg("上传成功");
    }


    @GetMapping("/getUserById/{id}")
    public Object getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return user!=null?MyResponse.success(user):null;
    }

    @GetMapping("/getUserByEmail/{email}")
    public Object getUserByEmail(@PathVariable("email") String email){
        User user=userService.getUserByEmail(email);
        return user!=null?MyResponse.success(user):null;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        User u = userService.login(user);

        return u!=null?MyResponse.success(u):MyResponse.error().msg("登录失败");
    }
	
}