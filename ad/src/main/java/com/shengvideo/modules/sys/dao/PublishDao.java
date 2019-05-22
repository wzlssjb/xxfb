package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.PublishEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * publish;发布主表
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@Mapper
public interface PublishDao extends BaseMapper<PublishEntity> {
    List<PublishEntity> findByNow(Map map);

    List<Date> findTimes(Map map);
}
