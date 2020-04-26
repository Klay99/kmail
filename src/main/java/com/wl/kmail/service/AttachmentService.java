package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Attachment;

public interface AttachmentService {

	Object getAllAttachment(PageParam<Attachment> pageParam);

    boolean removeAttachmentById(int id);

    Object addAttachment(Attachment attachment);

    boolean updateAttachment(Attachment attachment);

    Attachment getAttachmentById(int id);

}