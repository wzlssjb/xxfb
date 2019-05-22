package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ScountMaterialEntity;

import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-14 15:49:32
 */
public interface ScountMaterialService extends IService<ScountMaterialEntity> {

    PageUtils queryPage( Map<String, Object> params);


    PageUtils queryPageMaterial(Map<String, Object> params);
}

