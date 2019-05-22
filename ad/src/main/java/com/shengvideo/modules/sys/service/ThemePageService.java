package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ThemePageEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
public interface ThemePageService extends IService<ThemePageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ThemePageEntity> findByTid(Long tid);

    void delByTid(Long tid);
}

