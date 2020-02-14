package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.Draft;

import java.util.List;

@Mapper
public interface DraftDao {

    List<Draft> getAllDraft(Draft draft);

    int removeDraftById(int id);

    int addDraft(Draft draft);

    int updateDraft(Draft draft);

    Draft getDraftById(int id);

}