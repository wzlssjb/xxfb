package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.PublishDeviceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 发布设备;
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@Mapper
public interface PublishDeviceDao extends BaseMapper<PublishDeviceEntity> {
    List<PublishDeviceEntity> findByPid(Long pid);
    int selectOnCount(Long pid);
    void offLine(Map map);

    void clearAll(Long did);
    void clearMobile(Long did);
}
