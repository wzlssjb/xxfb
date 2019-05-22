package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ThemeClassEntity;

import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-12-22 13:25:15
 */
public interface ThemeClassService extends IService<ThemeClassEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

