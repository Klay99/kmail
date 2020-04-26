package com.wl.kmail.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.wl.kmail.config.exception.HttpCode;
import com.wl.kmail.config.exception.MyException;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.dao.MailDao;
import com.wl.kmail.model.Mail;

import java.util.List;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

	@Autowired
    MailDao mailDao;

	@Override
	public Object getAllMail(PageParam<Mail> pageParam){
	    // todo 根据时间查询
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }
        List<Mail> mailList=mailDao.getAllMail(pageParam.getModel());
        PageInfo<Mail> mailPageInfo = new PageInfo<Mail>(mailList);

        return mailPageInfo;
        
    }

    @Override
    public boolean removeMailById(int id){
    	return mailDao.removeMailById(id)==1;
    }

    @Override
    public Object addMail(Mail mail){
        mailDao.addMail(mail);

        return mailDao.getMailById(mail.getId());
    }

	@Override
    public boolean updateMail(Mail mail){
    	if(StringUtils.isEmpty(mail.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改mail时，id不能为空");
        }

        return mailDao.updateMail(mail)==1;
    }

    @Override
    public Mail getMailById(int id){
    	return mailDao.getMailById(id);
    	
    }

}
