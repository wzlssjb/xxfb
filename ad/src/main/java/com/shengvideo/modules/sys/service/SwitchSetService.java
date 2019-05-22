package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.SwitchSetEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-20 18:02:35
 */
public interface SwitchSetService extends IService<SwitchSetEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SwitchSetEntity> findByDeviceId(Long did,Integer ontime);

    public void deleteByDeviceId(long did);

    public void saveSwitchSet(Long[] dids, List<SwitchSetEntity> volSets);

}

