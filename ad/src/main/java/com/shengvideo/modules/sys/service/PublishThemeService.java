package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.PublishThemeEntity;

import java.util.Map;

/**
 * 发布节目;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
public interface PublishThemeService extends IService<PublishThemeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

