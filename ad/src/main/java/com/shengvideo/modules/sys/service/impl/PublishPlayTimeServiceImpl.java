package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.PublishPlayTimeDao;
import com.shengvideo.modules.sys.entity.PublishPlayTimeEntity;
import com.shengvideo.modules.sys.service.PublishPlayTimeService;


@Service("publishPlayTimeService")
public class PublishPlayTimeServiceImpl extends ServiceImpl<PublishPlayTimeDao, PublishPlayTimeEntity> implements PublishPlayTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PublishPlayTimeEntity> page = this.selectPage(
                new Query<PublishPlayTimeEntity>(params).getPage(),
                new EntityWrapper<PublishPlayTimeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PublishPlayTimeEntity> findByPid(Long pid){
        return baseMapper.findByPid(pid);
    }

}
