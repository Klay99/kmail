package com.wl.kmail.controller;

import com.wl.kmail.config.exception.MyResponse;
import com.wl.kmail.config.pagehelper.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wl.kmail.config.*;
import com.wl.kmail.model.Draft;
import com.wl.kmail.service.DraftService;

import javax.validation.Valid;

@Api(value = "draft模块接口",description = "这是一个草稿模块的接口文档")
@RestController
@Slf4j
@RequestMapping("draft")
@CrossOrigin
public class DraftController {

	@Autowired
    DraftService draftService;

	@ApiOperation("查询所有草稿 支持多条件分页排序查询")
    @PostMapping("/getAllDraft")
    public Object getAllDraft(@RequestBody PageParam<Draft> pageParam){
        return MyResponse.success(draftService.getAllDraft(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeDraftById/{id}")
    public Object removeDraftByDraftName(@PathVariable("id") int id){
        return draftService.removeDraftById(id)?MyResponse.success(null).msg("删除成功"):MyResponse.error().msg("删除失败");
    }

    @PostMapping("/addDraft")
    public Object addDraft(@RequestBody @Valid Draft draftParam){
        Draft draft=(Draft)draftService.addDraft(draftParam);
        return draft!=null?MyResponse.success(draft).
                msg("添加成功"):MyResponse.error().msg("添加失败");
    }

    @PutMapping("/updateDraft")
    public Object updateDraft(@RequestBody@Valid Draft draft){
        return draftService.updateDraft(draft)?MyResponse.success(null)
                .msg("修改成功"):MyResponse.error().msg("修改失败");
    }

    @GetMapping("/getDraftById/{id}")
    public Object getDraftById(@PathVariable("id") int id){
        Draft draft=draftService.getDraftById(id);
        return draft!=null?MyResponse.success(draft):null;
    }
	
}