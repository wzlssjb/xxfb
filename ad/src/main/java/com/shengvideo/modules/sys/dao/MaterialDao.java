package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
@Mapper
public interface MaterialDao extends BaseMapper<MaterialEntity> {
    List<MaterialEntity> list(Pagination page, Map map);

    long selectSize(Map map);
}
