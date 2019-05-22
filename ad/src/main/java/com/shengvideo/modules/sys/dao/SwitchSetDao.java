package com.shengvideo.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengvideo.modules.sys.entity.SwitchSetEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-20 18:02:35
 */
@Mapper
public interface SwitchSetDao extends BaseMapper<SwitchSetEntity> {

    List<SwitchSetEntity> selectByDeviceOntime(Map<String, Object> map);

    void deleteByDevice(Long did);


}
