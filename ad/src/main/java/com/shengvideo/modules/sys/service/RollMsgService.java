package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.RollMsgEntity;

import java.util.Map;

/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-22 11:00:55
 */
public interface RollMsgService extends IService<RollMsgEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Long[] dids, RollMsgEntity msg);
}

