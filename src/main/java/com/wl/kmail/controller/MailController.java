package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Mail;
import com.wl.kmail.service.MailService;

import javax.validation.Valid;

@Api(value = "mail模块接口",description = "这是一个mail模块的接口文档")
@RestController
@Slf4j
@RequestMapping("mail")
@CrossOrigin
public class MailController {

	@Autowired
    MailService mailService;

	@ApiOperation("查询所有mail 支持多条件分页排序查询")
    @PostMapping("/getAllMail")
    public Object getAllMail(@RequestBody PageParam<Mail> pageParam){
        return MyResponse.success(mailService.getAllMail(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeMailById/{id}")
    public Object removeMailByMailName(@PathVariable("id") int id){
        return mailService.removeMailById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addMail")
    public Object addMail(@RequestBody @Valid Mail mailParam){
        Mail mail=(Mail)mailService.addMail(mailParam);
        return mail!=null?MyResponse.success(mail).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateMail")
    public Object updateMail(@RequestBody@Valid Mail mail){
        return mailService.updateMail(mail)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getMailById/{id}")
    public Object getMailById(@PathVariable("id") int id){
        Mail mail=mailService.getMailById(id);
        return mail!=null?MyResponse.success(mail):null;
    }
	
}