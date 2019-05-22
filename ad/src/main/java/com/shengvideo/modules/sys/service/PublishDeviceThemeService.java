package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.PublishDeviceThemeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-12 13:22:37
 */
public interface PublishDeviceThemeService extends IService<PublishDeviceThemeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void subPlay(Long[] dids, Long[] tids);

    List<Long> pidList(Long[] dids, Long[] tids);

    int selectOnCount(Long pid, Long did);
}

