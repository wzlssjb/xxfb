package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.ElementEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:35:02
 */
@Mapper
public interface ElementDao extends BaseMapper<ElementEntity> {
    List<ElementEntity> findByPid(Long pid);

    void delByPid(Long id);
}
