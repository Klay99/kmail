package com.wl.kmail.model;

import lombok.Data;

import java.io.Serializable;

//@ApiModel(value = "user", description = "用户实体类")
@Data
public class User implements Serializable {

//    @ApiModelProperty(value = "用户id", name = "id")
    private int id;

//    @ApiModelProperty(value = "用户名", name = "username")
    private String username;

//    @ApiModelProperty(value = "用户密码", name = "password")
    private String password;

//    @ApiModelProperty(value = "用户邮箱", name = "email")
//    @NotEmpty(message = "邮箱不能为空")
    private String email;

//    @ApiModelProperty(value = "用户头像", name = "headImg")
    private String headImg;

//    @ApiModelProperty(value = "是否为新用户", name = "isNew")
    private int isNew;
}
