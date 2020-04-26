package com.wl.kmail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "Mail" ,description = "mail")
@Data // 自动生成get set 和构造器
public class Mail implements Serializable {
	// id of this mail
    @ApiModelProperty(value = "id of this mail" ,name = "id")
	private Integer id;
	// subject of this mail
    @ApiModelProperty(value = "subject of this mail" ,name = "subject")
	private String subject;
	// content of this mail
    @ApiModelProperty(value = "content of this mail" ,name = "content")
	private String content;
	// id of sender of this mail
    @ApiModelProperty(value = "id of sender of this mail" ,name = "senderId")
	private Integer senderId;
	// id of recipient of this mail
    @ApiModelProperty(value = "id of recipient of this mail" ,name = "recipientId")
	private Integer recipientId;
	// create time of this mail
    @ApiModelProperty(value = "create time of this mail" ,name = "createTime")
	private Date createTime;
	// 1: is not star 2: is star
    @ApiModelProperty(value = "0: is star 1: is not star" ,name = "isStar")
	private Integer isStar;
	// mail of sender
    @ApiModelProperty(value = "mail of sender" ,name = "senderMail")
	private String senderMail;
	// mail of recipient
    @ApiModelProperty(value = "mail of recipient" ,name = "recipientMail")
	private String recipientMail;
	// 1: is not read 2: is read
    @ApiModelProperty(value = "mail of isRead" ,name = "isRead")
	private Integer isRead;
	// 1: is not send 2: is send
    @ApiModelProperty(value = "mail of isRead" ,name = "isRead")
	private Integer isSend;
	// 1: is not discard 2: is discard
    @ApiModelProperty(value = "mail of isRead" ,name = "isRead")
	private Integer isDiscard;
	// 1: is not deleted 2: is deleted
    @ApiModelProperty(value = "mail of isRead" ,name = "isRead")
	private Integer isDeleted;
    // sender head img
	@ApiModelProperty(value = "sender head img",name = "senderHeadImg")
    private String senderHeadImg;
	// 1: is not draft 2: is draft
	@ApiModelProperty(value = "mail of isDraft" ,name = "isDraft")
	private Integer isDraft;
}