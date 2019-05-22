package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.SlogDao;
import com.shengvideo.modules.sys.entity.SlogEntity;
import com.shengvideo.modules.sys.service.SlogService;


@Service("slogService")
public class SlogServiceImpl extends ServiceImpl<SlogDao, SlogEntity> implements SlogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<SlogEntity> page = this.selectPage(
//                new Query<SlogEntity>(params).getPage(),
//                new EntityWrapper<SlogEntity>()
//        );
        Page<SlogEntity> page = new Query<SlogEntity>(params).getPage();
        return new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }

}
