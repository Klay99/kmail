package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import com.wl.kmail.model.Attachment;
import com.wl.kmail.model.User;
import com.wl.kmail.service.AttachmentService;
import com.wl.kmail.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Mail;
import com.wl.kmail.service.MailService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Api(value = "mail模块接口", description = "这是一个mail模块的接口文档")
@RestController
@Slf4j
@RequestMapping("mail")
@CrossOrigin
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @Value("${app.filePath}")
    private String filePath;

    @ApiOperation("查询所有mail 支持多条件分页排序查询")
    @PostMapping("/getAllMail")
    public Object getAllMail(@RequestBody PageParam<Mail> pageParam) {
        return MyResponse.success(mailService.getAllMail(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeMailById/{id}")
    public Object removeMailByMailName(@PathVariable("id") int id) {
        return mailService.removeMailById(id) ? MyResponse.success(null).msg("删除成功") : MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addMail")
    public Object addMail(@RequestBody @Valid Mail mailParam) {
        Mail mail = (Mail) mailService.addMail(mailParam);
        return mail != null ? MyResponse.success(mail).
                msg("添加成功") : MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateMail")
    public Object updateMail(@RequestBody @Valid Mail mail) {
        return mailService.updateMail(mail) ? MyResponse.success(null)
                .msg("修改成功") : MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getMailById/{id}")
    public Object getMailById(@PathVariable("id") int id) {
        Mail mail = mailService.getMailById(id);
        return mail != null ? MyResponse.success(mail) : null;
    }

    @ApiOperation("添加到草稿箱")
    @PostMapping("/addDraft")
    public Object addDraft(Mail mail, @RequestParam("recipients") ArrayList<String> recipients, @RequestParam(name = "files", required = false) ArrayList<MultipartFile> multiFiles) {
        // 添加附件
        if (multiFiles != null) {
            if (!addAttachments(multiFiles)) {
                return MyResponse.error().msg("文件上传失败");
            }
        }

        // 添加邮件到数据库
        ArrayList<Mail> mails = new ArrayList<>();

        if (recipients == null) {
            if (multiFiles != null) {
                for (MultipartFile multiFile : multiFiles) {
                    Mail m = new Mail();
                    BeanUtils.copyProperties(mail, m);
                    m.setIsDraft(2);
                    mailService.addMail(m);
                    Attachment attachment = new Attachment();
                    attachment.setName(multiFile.getOriginalFilename());
                    attachment.setSenderId(mail.getSenderId());
                    attachment.setSenderMail(mail.getSenderMail());
                    attachment.setAddress(filePath + "draft/" + mail.getSenderId() + "/" + multiFile.getOriginalFilename());
                    attachment.setMailId(m.getId());
                    attachmentService.addAttachment(attachment);
                    mails.add(m);
                }
            }
        } else {
            for (String recipient : recipients) {
                Mail m = new Mail();
                BeanUtils.copyProperties(mail, m);
                User u = userService.getUserByEmail(recipient);
                if (u != null) m.setRecipientId(u.getId()); // 收件人是否为kmail用户，如果是，添加收件人id
                m.setRecipientMail(recipient); // 添加收件人邮箱
                m.setIsDraft(2);
                mailService.addMail(m);
                log.info("mail id: " + m.getId());
                // set attachment
                if (multiFiles != null) {
                    for (MultipartFile multiFile : multiFiles) {
                        Attachment attachment = new Attachment();
                        attachment.setName(multiFile.getOriginalFilename());
                        if (u != null) attachment.setRecipientId(u.getId());
                        attachment.setRecipientMail(recipient);
                        attachment.setSenderId(mail.getSenderId());
                        attachment.setSenderMail(mail.getSenderMail());
                        attachment.setAddress(filePath + "draft/" + multiFile.getOriginalFilename());
                        attachment.setMailId(m.getId());
                        attachmentService.addAttachment(attachment);
                    }
                }
                mails.add(m);
            }
        }

        return MyResponse.success(mails).msg("add success");
    }

    private boolean addAttachments(ArrayList<MultipartFile> multiFiles) {
        String basePath = filePath + "draft/";
        File base = new File(basePath);
        try {
            if (!base.exists()) {
                base.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (MultipartFile multiFile : multiFiles) {
            String name = multiFile.getOriginalFilename();
            if (!multiFile.isEmpty()) {
                String path = basePath + "/" + name;
                File file = new File(path);
                try {
                    if (!file.exists()) {
                        if (!file.createNewFile()) {
                            return false;
                        }
                    }
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                    out.write(multiFile.getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

}