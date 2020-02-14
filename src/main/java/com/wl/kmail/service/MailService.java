package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Mail;

public interface MailService {

	Object getAllMail(PageParam<Mail> pageParam);

    boolean removeMailById(int id);

    Object addMail(Mail mail);

    boolean updateMail(Mail mail);

    Mail getMailById(int id);

}