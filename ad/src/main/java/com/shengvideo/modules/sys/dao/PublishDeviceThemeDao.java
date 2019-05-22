package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.PublishDeviceThemeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-12 13:22:37
 */
@Mapper
public interface PublishDeviceThemeDao extends BaseMapper<PublishDeviceThemeEntity> {
	void subPlay(Map<String, Object> map);

	List<Long> pidList(Map<String, Object> map);

	int selectOnCount(@Param("pid") Long pid, @Param("did") Long did);
}
