package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.PublishThemeDao;
import com.shengvideo.modules.sys.entity.PublishThemeEntity;
import com.shengvideo.modules.sys.service.PublishThemeService;


@Service("publishThemeService")
public class PublishThemeServiceImpl extends ServiceImpl<PublishThemeDao, PublishThemeEntity> implements PublishThemeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PublishThemeEntity> page = this.selectPage(
                new Query<PublishThemeEntity>(params).getPage(),
                new EntityWrapper<PublishThemeEntity>()
        );

        return new PageUtils(page);
    }

}
