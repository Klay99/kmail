package com.wl.kmail.config.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    private static final String SHOUQUANMA = "augexsoedrkzjjjd";

    public static MimeMessage createSimpleMail(Session session, String authCode, String emailAddresss)
            throws Exception {
// 创建邮件对象
        MimeMessage message = new MimeMessage(session);
// 指明邮件的发件人
        message.setFrom(new InternetAddress("1106390376@qq.com"));
// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddresss));
// 邮件的标题
        message.setSubject("找回密码");
// 邮件的文本内容
        message.setContent("【kmail】尊敬的用户：您的校验码：" + authCode + "，工作人员不会索取，请勿泄漏。", "text/html;charset=UTF-8");
// 返回创建好的邮件对象
        return message;
    }


    public static void sendEmail(String mima,String emailAddresss) throws Exception {
        Properties prop = new Properties();
// 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
// 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
// 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
// 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");

// 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

// 创建session
        Session session = Session.getInstance(prop);
// 通过session得到transport对象
        Transport ts = session.getTransport();
// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect("smtp.qq.com", "1106390376", SHOUQUANMA);
// 创建邮件
        Message message = createSimpleMail(session,mima,emailAddresss);
// 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static void main(String[] args) throws Exception {
        sendEmail("123456","3529798628@qq.com");
    }
}
