package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.Mail;

import java.util.List;

@Mapper
public interface MailDao {

    List<Mail> getAllMail(Mail mail);

    int removeMailById(int id);

    int addMail(Mail mail);

    int updateMail(Mail mail);

    Mail getMailById(int id);

}