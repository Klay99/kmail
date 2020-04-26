package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "User" ,description = "用户")
@Data // 自动生成get set 和构造器
public class User implements Serializable {
	// user id
    @ApiModelProperty(value = "user id" ,name = "id")
	private Integer id;
	// username
    @ApiModelProperty(value = "username" ,name = "username")
	private String username;
	// password
    @ApiModelProperty(value = "password" ,name = "password")
	private String password;
	// user email
    @ApiModelProperty(value = "user email" ,name = "email")
	private String email;
	// head img
    @ApiModelProperty(value = "head img" ,name = "headImg")
	private String headImg;
	// 0:is a new user 1:is not a new user
    @ApiModelProperty(value = "0:is a new user 1:is not a new user" ,name = "isNew")
	private Integer isNew;
	// authorization code
    @ApiModelProperty(value = "authorization code" ,name = "authCode")
	private String authCode;
}