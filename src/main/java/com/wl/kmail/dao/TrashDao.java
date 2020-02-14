package com.wl.kmail.dao;

import org.apache.ibatis.annotations.*;
import com.wl.kmail.model.Trash;

import java.util.List;

@Mapper
public interface TrashDao {

    List<Trash> getAllTrash(Trash trash);

    int removeTrashById(int id);

    int addTrash(Trash trash);

    int updateTrash(Trash trash);

    Trash getTrashById(int id);
    
}