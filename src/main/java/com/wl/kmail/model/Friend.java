package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Friend" ,description = "通讯录好友")
@Data // 自动生成get set 和构造器
public class Friend implements Serializable {
	// 
    @ApiModelProperty(value = "" ,name = "id")
	private Integer id;
	// 用户id
    @ApiModelProperty(value = "用户id" ,name = "userId")
	private Integer userId;
	// 用户邮箱地址
    @ApiModelProperty(value = "用户邮箱地址" ,name = "userMail")
	private String userMail;
	// 用户名
    @ApiModelProperty(value = "用户名" ,name = "username")
	private String username;
	// 好友id
    @ApiModelProperty(value = "好友id" ,name = "friendId")
	private Integer friendId;
	// 好友名
    @ApiModelProperty(value = "好友名" ,name = "friendName")
	private String friendName;
	// 好友邮箱地址
    @ApiModelProperty(value = "好友邮箱地址" ,name = "friendMail")
	private String friendMail;
	// 用户头像
    @ApiModelProperty(value = "用户头像" ,name = "userHeadImg")
	private String userHeadImg;
	// 好友头像
    @ApiModelProperty(value = "好友头像" ,name = "friendHeadImg")
	private String friendHeadImg;
	// 创建时间
    @ApiModelProperty(value = "创建时间" ,name = "createTime")
	private Date createTime;
	// 1：不是好友   2：是好友
    @ApiModelProperty(value = "1：不是好友   2：是好友" ,name = "isFriend")
	private Integer isFriend;
	// 1：未删除  2：已删除
    @ApiModelProperty(value = "1：未删除  2：已删除" ,name = "isDeleted")
	private Integer isDeleted;
    // 所属组id
	@ApiModelProperty(value = "所属组id", name = "groupId")
	private Integer groupId;
}