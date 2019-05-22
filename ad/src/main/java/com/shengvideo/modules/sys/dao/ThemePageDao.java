package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.ThemePageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@Mapper
public interface ThemePageDao extends BaseMapper<ThemePageEntity> {
	List<ThemePageEntity> findByTid(Long tid);

	void delByTid(Long tid);
}
