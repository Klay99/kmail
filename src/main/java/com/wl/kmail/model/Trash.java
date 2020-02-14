package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Trash" ,description = "trash")
@Data // 自动生成get set 和构造器
public class Trash implements Serializable {
	// id of this trash
    @ApiModelProperty(value = "id of this trash" ,name = "id")
	private Integer id;
	// owner of this trash
    @ApiModelProperty(value = "owner of this trash" ,name = "owner")
	private Integer owner;
	// sender of this trash
    @ApiModelProperty(value = "sender of this trash" ,name = "sender")
	private Integer sender;
	// recipient of this trash
    @ApiModelProperty(value = "recipient of this trash" ,name = "recipient")
	private Integer recipient;
	// create time of this trash
    @ApiModelProperty(value = "create time of this trash" ,name = "createTime")
	private Date createTime;
}