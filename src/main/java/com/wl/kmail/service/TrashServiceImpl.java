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
import com.wl.kmail.dao.TrashDao;
import com.wl.kmail.model.Trash;

import java.util.List;

@Slf4j
@Service
public class TrashServiceImpl implements TrashService {

	@Autowired
    TrashDao trashDao;

	@Override
	public Object getAllTrash(PageParam<Trash> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }

        List<Trash> trashList=trashDao.getAllTrash(pageParam.getModel());
        PageInfo<Trash> trashPageInfo = new PageInfo<Trash>(trashList);

        return trashPageInfo;
        
    }

    @Override
    public boolean removeTrashById(int id){
    	return trashDao.removeTrashById(id)==1;
    }

    @Override
    public Object addTrash(Trash trash){
        trashDao.addTrash(trash);

        return trashDao.getTrashById(trash.getId());
    }

	@Override
    public boolean updateTrash(Trash trash){
    	if(StringUtils.isEmpty(trash.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改trash时，id不能为空");
        }

        return trashDao.updateTrash(trash)==1;
    }

    @Override
    public Trash getTrashById(int id){
    	return trashDao.getTrashById(id);
    	
    }

}
