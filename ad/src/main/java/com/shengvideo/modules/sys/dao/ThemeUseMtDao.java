package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.ThemeUseMtEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@Mapper
public interface ThemeUseMtDao extends BaseMapper<ThemeUseMtEntity> {
	void delByTheme(Long tid);
}
