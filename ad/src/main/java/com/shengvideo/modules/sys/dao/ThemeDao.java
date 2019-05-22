package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import com.shengvideo.modules.sys.entity.ThemeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@Mapper
public interface ThemeDao extends BaseMapper<ThemeEntity> {

	List<ThemeEntity> list(Pagination page, Map map);

	ThemeEntity findById(Long id);

	List<ThemeEntity> findByPublish(Long pid);

	List<ThemeEntity> findByPublishAndDevice(Map<String, Object> param);

	List<ThemeEntity> findByDevices(Long[] ids);
}
