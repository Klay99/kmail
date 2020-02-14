package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Mail" ,description = "")
@Data // 自动生成get set 和构造器
public class Mail implements Serializable {
	// id of this mail
    @ApiModelProperty(value = "id of this mail" ,name = "id")
	private Integer id;
	// theme of this mail
    @ApiModelProperty(value = "theme of this mail" ,name = "theme")
	private String theme;
	// content of this mail
    @ApiModelProperty(value = "content of this mail" ,name = "content")
	private String content;
	// sender of this mail
    @ApiModelProperty(value = "sender of this mail" ,name = "sender")
	private Integer sender;
	// recipient of this mail
    @ApiModelProperty(value = "recipient of this mail" ,name = "recipient")
	private Integer recipient;
	// create time of this mail
    @ApiModelProperty(value = "create time of this mail" ,name = "createTime")
	private Date createTime;
	// 0: is star 1: is not star
    @ApiModelProperty(value = "0: is star 1: is not star" ,name = "isStar")
	private Integer isStar;
}