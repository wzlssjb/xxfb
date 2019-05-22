package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shengvideo.modules.sys.entity.ScountThemeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-14 15:49:32
 */
@Mapper
public interface ScountThemeDao extends BaseMapper<ScountThemeEntity> {

    List<ScountThemeEntity> list(Pagination page, Map<String, Object> map);
    List<ScountThemeEntity> listByTheme(Pagination page, Map<String, Object> map);
}
