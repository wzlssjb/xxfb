package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.PublishDeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 发布设备;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
public interface PublishDeviceService extends IService<PublishDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PublishDeviceEntity> findByPid(Long pid);

    int selectOnCount(Long pid);

    void offLine(Long[] ids, Long pid);

    void clearAll(Long[] dids);

    void clearMobile(Long[] dids);
}

