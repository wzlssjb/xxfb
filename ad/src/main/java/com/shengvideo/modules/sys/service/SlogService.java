package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.SlogEntity;

import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-13 16:51:06
 */
public interface SlogService extends IService<SlogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

