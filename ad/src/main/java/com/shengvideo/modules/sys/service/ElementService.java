package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ElementEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
public interface ElementService extends IService<ElementEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ElementEntity> findByPid(Long tid);

    void delByPid(Long tid);
}

