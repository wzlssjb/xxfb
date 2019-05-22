package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.VolSetEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-19 16:26:50
 */
@Mapper
public interface VolSetDao extends BaseMapper<VolSetEntity> {
	List<VolSetEntity> selectByDevice(Long did);
    void deleteByDevice(Long did);
}
