package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Attachment;
import com.wl.kmail.service.AttachmentService;

import javax.validation.Valid;

@Api(value = "attachment模块接口",description = "这是一个模块的接口文档")
@RestController
@Slf4j
@RequestMapping("attachment")
public class AttachmentController {

	@Autowired
    AttachmentService attachmentService;

	@ApiOperation("查询所有 支持多条件分页排序查询")
    @PostMapping("/getAllAttachment")
    public Object getAllAttachment(@RequestBody PageParam<Attachment> pageParam){
        return MyResponse.success(attachmentService.getAllAttachment(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeAttachmentById/{id}")
    public Object removeAttachmentByAttachmentName(@PathVariable("id") int id){
        return attachmentService.removeAttachmentById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addAttachment")
    public Object addAttachment(@RequestBody @Valid Attachment attachmentParam){
        Attachment attachment=(Attachment)attachmentService.addAttachment(attachmentParam);
        return attachment!=null?MyResponse.success(attachment).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateAttachment")
    public Object updateAttachment(@RequestBody@Valid Attachment attachment){
        return attachmentService.updateAttachment(attachment)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getAttachmentById/{id}")
    public Object getAttachmentById(@PathVariable("id") int id){
        Attachment attachment=attachmentService.getAttachmentById(id);
        return attachment!=null?MyResponse.success(attachment):null;
    }
	
}