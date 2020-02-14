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
import com.wl.kmail.dao.DraftDao;
import com.wl.kmail.model.Draft;

import java.util.List;

@Slf4j
@Service
public class DraftServiceImpl implements DraftService {

	@Autowired
    DraftDao draftDao;

	@Override
	public Object getAllDraft(PageParam<Draft> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }

        List<Draft> draftList=draftDao.getAllDraft(pageParam.getModel());
        PageInfo<Draft> draftPageInfo = new PageInfo<Draft>(draftList);

        return draftPageInfo;
        
    }

    @Override
    public boolean removeDraftById(int id){
    	return draftDao.removeDraftById(id)==1;
    }

    @Override
    public Object addDraft(Draft draft){
        draftDao.addDraft(draft);

        return draftDao.getDraftById(draft.getId());
    }

	@Override
    public boolean updateDraft(Draft draft){
    	if(StringUtils.isEmpty(draft.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改draft时，id不能为空");
        }

        return draftDao.updateDraft(draft)==1;
    }

    @Override
    public Draft getDraftById(int id){
    	return draftDao.getDraftById(id);
    	
    }

}
