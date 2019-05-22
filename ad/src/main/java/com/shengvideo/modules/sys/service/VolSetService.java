package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.VolSetEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-19 16:26:50
 */
public interface VolSetService extends IService<VolSetEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<VolSetEntity> selectByDevice(Long did);

    void deleteByDevice(Long did);

    void saveVolSets(Long[] dids, List<VolSetEntity> volSets);
}

