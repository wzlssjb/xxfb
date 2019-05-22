package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ThemeEntity;
import com.shengvideo.modules.sys.vo.PublishVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
public interface ThemeService extends IService<ThemeEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryTempPage(Map<String, Object> params);

    ThemeEntity findById(Long id);

    List<ThemeEntity> findByDevices(Long[] ids);

    List<ThemeEntity> findByPublish(Long pid);

    List<ThemeEntity> findByPublishAndDevice(Long pid, Long did);

    List<ThemeEntity> findByPublishAndDeviceNormal(Long pid, Long did);

    List<ThemeEntity> findByPublishAndDevice(Long pid, Long did, Boolean status);

    void updateInfo(ThemeEntity theme);

    List<ThemeEntity> findThemeByDeviceOnTime(Long did);

    List<PublishVo> findPublishByDeviceOnTime(Long did);

    List<PublishVo> findPublishByDeviceAndRangeTime(Long did, Date startTime, Date endTime);
}

