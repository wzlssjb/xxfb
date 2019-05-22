package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.SgroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@Mapper
public interface SgroupDao extends BaseMapper<SgroupEntity> {

//    SgroupEntity queryByRoutId(Integer routeId);

    List<SgroupEntity> queryByParent(Long gid);

    void deleteGroupId(long id);
}
