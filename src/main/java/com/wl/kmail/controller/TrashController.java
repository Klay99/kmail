package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.pagehelper.PageParam;
import com.wl.kmail.model.Trash;
import com.wl.kmail.service.TrashService;

import javax.validation.Valid;

@Api(value = "trash模块接口",description = "这是一个trash模块的接口文档")
@RestController
@Slf4j
public class TrashController {

	@Autowired
    TrashService trashService;

	@ApiOperation("查询所有trash 支持多条件分页排序查询")
    @PostMapping("/getAllTrash")
    public Object getAllTrash(@RequestBody PageParam<Trash> pageParam){
        return MyResponse.success(trashService.getAllTrash(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeTrashById/{id}")
    public Object removeTrashByTrashName(@PathVariable("id") int id){
        return trashService.removeTrashById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addTrash")
    public Object addTrash(@RequestBody @Valid Trash trashParam){
        Trash trash=(Trash)trashService.addTrash(trashParam);
        return trash!=null?MyResponse.success(trash).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateTrash")
    public Object updateTrash(@RequestBody@Valid Trash trash){
        return trashService.updateTrash(trash)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getTrashById/{id}")
    public Object getTrashById(@PathVariable("id") int id){
        Trash trash=trashService.getTrashById(id);
        return trash!=null?MyResponse.success(trash):null;
    }
	
}