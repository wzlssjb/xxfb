package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.ThemeClassDao;
import com.shengvideo.modules.sys.entity.ThemeClassEntity;
import com.shengvideo.modules.sys.service.ThemeClassService;


@Service("themeClassService")
public class ThemeClassServiceImpl extends ServiceImpl<ThemeClassDao, ThemeClassEntity> implements ThemeClassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ThemeClassEntity> page = this.selectPage(
                new Query<ThemeClassEntity>(params).getPage(),
                new EntityWrapper<ThemeClassEntity>()
        );

        return new PageUtils(page);
    }

}
