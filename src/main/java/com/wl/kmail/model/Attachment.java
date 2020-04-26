package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Attachment" ,description = "")
@Data // 自动生成get set 和构造器
public class Attachment implements Serializable {
	// 
    @ApiModelProperty(value = "" ,name = "id")
	private Integer id;
	// 
    @ApiModelProperty(value = "" ,name = "name")
	private String name;
	// 
    @ApiModelProperty(value = "" ,name = "address")
	private String address;
	// 
    @ApiModelProperty(value = "" ,name = "senderId")
	private Integer senderId;
	// 
    @ApiModelProperty(value = "" ,name = "senderMail")
	private String senderMail;
	// 
    @ApiModelProperty(value = "" ,name = "recipientId")
	private Integer recipientId;
	//
    @ApiModelProperty(value = "" ,name = "recipientMail")
	private String recipientMail;
	//
    @ApiModelProperty(value = "" ,name = "mailId")
	private Integer mailId;
}
