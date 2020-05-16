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
import com.wl.kmail.dao.AttachmentDao;
import com.wl.kmail.model.Attachment;

import java.util.List;

@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
    AttachmentDao attachmentDao;

	@Override
	public Object getAllAttachment(PageParam<Attachment> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }

        List<Attachment> attachmentList=attachmentDao.getAllAttachment(pageParam.getModel());
        PageInfo<Attachment> attachmentPageInfo = new PageInfo<Attachment>(attachmentList);

        return attachmentPageInfo;
        
    }

    @Override
    public boolean removeAttachmentById(int id){
    	return attachmentDao.removeAttachmentById(id)==1;
    }

    @Override
    public boolean removeAttachmentByMailId(int mailId) {
        return attachmentDao.removeAttachmentByMailId(mailId) != 0;
    }

    @Override
    public Object addAttachment(Attachment attachment){
        attachmentDao.addAttachment(attachment);

        return attachmentDao.getAttachmentById(attachment.getId());
    }

	@Override
    public boolean updateAttachment(Attachment attachment){
    	if(StringUtils.isEmpty(attachment.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改attachment时，id不能为空");
        }

        return attachmentDao.updateAttachment(attachment)==1;
    }

    @Override
    public Attachment getAttachmentById(int id){
    	return attachmentDao.getAttachmentById(id);
    	
    }

    @Override
    public void deleteByMailId(Integer id) {
        List<Attachment> list = attachmentDao.getAttachmentByMailId(id);
        for (Attachment attachment : list) {
            attachmentDao.removeAttachmentById(attachment.getId());
        }
    }

}
