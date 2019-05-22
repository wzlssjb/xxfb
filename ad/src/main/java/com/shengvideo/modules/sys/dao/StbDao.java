package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.vo.OpVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@Mapper
public interface StbDao extends BaseMapper<StbEntity> {
    List<StbEntity> list(Pagination page, Map map);

    StbEntity findByMac(Map map);

    StbEntity findById(Long id);

    List<StbEntity> findByPublish(Long pid);

    int selectCount(Map map);

    int selectCountByGroup(Long gid);

    void updateVol(Map map);

    void updateDeleteGroupId(Long id);

    List<OpVo> resolution();

    void initOnline();
}
