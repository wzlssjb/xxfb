package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.MaterialEntity;


import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
public interface MaterialService extends IService<MaterialEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long selectSize(Map map);

    Long selectSizeByType(int type);
}

