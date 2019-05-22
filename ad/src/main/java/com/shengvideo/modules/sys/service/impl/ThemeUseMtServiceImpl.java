package com.shengvideo.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.ThemeUseMtDao;
import com.shengvideo.modules.sys.entity.ThemeUseMtEntity;
import com.shengvideo.modules.sys.service.ThemeUseMtService;


@Service("themeUseMtService")
public class ThemeUseMtServiceImpl extends ServiceImpl<ThemeUseMtDao, ThemeUseMtEntity> implements ThemeUseMtService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ThemeUseMtEntity> page = this.selectPage(
                new Query<ThemeUseMtEntity>(params).getPage(),
                new EntityWrapper<ThemeUseMtEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void delByTheme(Long tid){
        baseMapper.delByTheme(tid);
    }

}
