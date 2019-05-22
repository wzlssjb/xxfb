package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.ThemePageDao;
import com.shengvideo.modules.sys.entity.ThemePageEntity;
import com.shengvideo.modules.sys.service.ThemePageService;


@Service("themePageService")
public class ThemePageServiceImpl extends ServiceImpl<ThemePageDao, ThemePageEntity> implements ThemePageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ThemePageEntity> page = this.selectPage(
                new Query<ThemePageEntity>(params).getPage(),
                new EntityWrapper<ThemePageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ThemePageEntity> findByTid(Long tid){
        return baseMapper.findByTid(tid);
    }

    @Override
    public void delByTid(Long tid){
         baseMapper.delByTid(tid);
    }


}
