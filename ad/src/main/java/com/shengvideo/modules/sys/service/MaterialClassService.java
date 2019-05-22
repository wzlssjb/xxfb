package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.MaterialClassEntity;


import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
public interface MaterialClassService extends IService<MaterialClassEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

