package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.PublishPlayTimeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 播放时间段;
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@Mapper
public interface PublishPlayTimeDao extends BaseMapper<PublishPlayTimeEntity> {
	List<PublishPlayTimeEntity> findByPid(Long pid);
}
