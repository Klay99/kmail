package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Attachment;
import com.wl.kmail.model.Mail;
import com.wl.kmail.model.User;
import com.wl.kmail.service.AttachmentService;
import com.wl.kmail.service.MailService;
import com.wl.kmail.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.EmailPopulatingBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Api(value = "email模块接口", description = "这是一个邮件发送等业务模块的接口文档")
@RestController
@Slf4j
@RequestMapping("email")
@CrossOrigin
public class EmailController {

    @Value("${app.filePath}")
    private String filePath;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation("检查邮箱授权码")
    @PostMapping("/checkAuthCode")
    public Object checkAuthCode(@RequestBody PageParam<User> pageParam) {
        String username = pageParam.getModel().getEmail();
        String password = pageParam.getModel().getAuthCode();
        String[] strings = pageParam.getModel().getEmail().split("@");
        String host = "smtp." + strings[1];
        Integer port = 587; // 465 / 25
        Email email1 = EmailBuilder.startingBlank()
                .from(strings[0], username)
                .to("kmail", "3529798628@qq.com")
                .withSubject("检查授权码是否可用")
                .withPlainText("检查授权码是否可用")
                .buildEmail();
        log.info("\n\n===================\n" + email1.getRecipients().get(0).getAddress());
        MailerBuilder.withSMTPServer(host, port, username, password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer()
                .sendMail(email1);
        return MyResponse.success("send success").msg("success");
    }

    @ApiOperation("多功能邮件发送")
    @PostMapping("/send")
    public Object send(Mail mail, @RequestParam("recipients") ArrayList<String> recipients, @RequestParam(name = "files", required = false) ArrayList<MultipartFile> multiFiles) {
        // 截取发件人信息
        User sender = userService.getUserByEmail(mail.getSenderMail());
        String[] strings = sender.getEmail().split("@");
        // 连接邮件服务器所需的参数：host，port，username，password
        String host = "smtp." + strings[1];
        Integer port = 587; // 465 / 25
        String username = strings[0];
        String password = sender.getAuthCode();
        // 添加邮件主体
        EmailPopulatingBuilder builder = EmailBuilder.startingBlank()
                .from(sender.getUsername(), mail.getSenderMail())
                .withSubject(mail.getSubject())
                .withPlainText(mail.getContent());
        // 添加收件人
        for (String recipient : recipients) {
            User u = userService.getUserByEmail(recipient);
            if (u == null) {
                builder = builder.to(recipient);
            } else {
                builder = builder.to(u.getUsername(), recipient);
            }
        }
        // 添加附件
        if (multiFiles != null) {
            if (!addAttachments(mail.getSenderId() ,builder, multiFiles)) {
                return MyResponse.error().msg("文件上传失败");
            }
        }
        // 发送邮件
        Email email = builder.buildEmail();
        MailerBuilder
                .withSMTPServer(host, port, username, password)
                .buildMailer()
                .sendMail(email);

        // 添加邮件到数据库
        ArrayList<Mail> mails = new ArrayList<>();
        for (String recipient : recipients) {
            Mail m = new Mail();
            BeanUtils.copyProperties(mail, m);
            User u = userService.getUserByEmail(recipient);
            if (u != null) m.setRecipientId(u.getId()); // 收件人是否为kmail用户，如果是，添加收件人id
            m.setRecipientMail(recipient); // 添加收件人邮箱
            m.setIsSend(2);
            if (mail.getId() == null) {
                mailService.addMail(m);
            } else {
                m.setIsDraft(1);
                mailService.updateMail(m);
                attachmentService.removeAttachmentByMailId(mail.getId());
            }
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
                    attachment.setAddress(filePath + "file/" + multiFile.getOriginalFilename());
                    attachment.setMailId(m.getId());
                    attachmentService.addAttachment(attachment);
                }
            }
            mails.add(m);
        }

        return MyResponse.success(mails).msg("send success");
    }

    private boolean addAttachments(int id, EmailPopulatingBuilder builder, ArrayList<MultipartFile> multiFiles) {
        for (MultipartFile multiFile : multiFiles) {
            String name = multiFile.getOriginalFilename();
            if (!multiFile.isEmpty()) {
                String path = filePath + "file/" + name;
                File file = new File(path);
                try {
                    if (!file.exists()) {
                        if(!file.createNewFile()) {
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
                FileDataSource source = new FileDataSource(file);
                builder.withAttachment(name, source);
            }
        }
        return true;
    }

}
