package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Trash;

public interface TrashService {

	Object getAllTrash(PageParam<Trash> pageParam);

    boolean removeTrashById(int id);

    Object addTrash(Trash trash);

    boolean updateTrash(Trash trash);

    Trash getTrashById(int id);

}