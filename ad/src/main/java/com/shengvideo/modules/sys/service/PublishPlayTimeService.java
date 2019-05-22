package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.PublishPlayTimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 播放时间段;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
public interface PublishPlayTimeService extends IService<PublishPlayTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PublishPlayTimeEntity> findByPid(Long pid);
}

