package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Draft" ,description = "草稿")
@Data // 自动生成get set 和构造器
public class Draft implements Serializable {
	// draft id
    @ApiModelProperty(value = "draft id" ,name = "id")
	private Integer id;
	// owner of this draft
    @ApiModelProperty(value = "owner of this draft" ,name = "owner")
	private Integer owner;
	// recipient of this draft
    @ApiModelProperty(value = "recipient of this draft" ,name = "recipient")
	private Integer recipient;
	// theme of this draft
    @ApiModelProperty(value = "theme of this draft" ,name = "theme")
	private String theme;
	// content of this draft
    @ApiModelProperty(value = "content of this draft" ,name = "content")
	private String content;
	// create time of this draft
    @ApiModelProperty(value = "create time of this draft" ,name = "createTime")
	private Date createTime;
}