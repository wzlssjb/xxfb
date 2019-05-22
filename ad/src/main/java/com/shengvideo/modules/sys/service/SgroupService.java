package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.SgroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
public interface SgroupService extends IService<SgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SgroupEntity> getNodes(long pid, boolean notNull);

//    SgroupEntity queryByRoutId(Integer routeId);
    public void deleteGroupId(long id);

}

