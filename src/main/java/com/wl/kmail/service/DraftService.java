package com.wl.kmail.service;

import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Draft;

public interface DraftService {

	Object getAllDraft(PageParam<Draft> pageParam);

    boolean removeDraftById(int id);

    Object addDraft(Draft draft);

    boolean updateDraft(Draft draft);

    Draft getDraftById(int id);

}