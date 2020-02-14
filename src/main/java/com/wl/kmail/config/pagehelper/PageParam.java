package com.wl.kmail.config.pagehelper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: kmail
 * @description: pagehelper 分页插件
 * @author: Koty
 * @create: 2020-02-14 18:46
 **/
@ApiModel(value = "pageParam", description = "分页查询条件参数")
@Data
public class PageParam<Model> {
    @ApiModelProperty(value = "条件查询的对象", name = "model")
    private Model model;

    @ApiModelProperty(value = "排序参数", name = "orderParams")
    private String[] orderParams;

    @ApiModelProperty(value = "页码", name = "pageNum")
    private int pageNum;

    @ApiModelProperty(value = "每页记录条数", name = "pageSize")
    private int pageSize;
}
