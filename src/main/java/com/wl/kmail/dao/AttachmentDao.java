package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.Attachment;

import java.util.List;

@Mapper
public interface AttachmentDao {

    List<Attachment> getAllAttachment(Attachment attachment);

    int removeAttachmentById(int id);

    int removeAttachmentByMailId(int mailId);

    int addAttachment(Attachment attachment);

    int updateAttachment(Attachment attachment);

    Attachment getAttachmentById(int id);

    List<Attachment> getAttachmentByMailId(int mailId);
    
}