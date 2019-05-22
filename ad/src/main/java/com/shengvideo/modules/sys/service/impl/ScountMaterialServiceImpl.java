package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.ScountMaterialDao;
import com.shengvideo.modules.sys.entity.ScountMaterialEntity;
import com.shengvideo.modules.sys.service.ScountMaterialService;


@Service("scountMaterialService")
public class ScountMaterialServiceImpl extends ServiceImpl<ScountMaterialDao, ScountMaterialEntity> implements ScountMaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<ScountMaterialEntity> page = this.selectPage(
//                new Query<ScountMaterialEntity>(params).getPage(),
//                new EntityWrapper<ScountMaterialEntity>()
//        );
//        return new PageUtils(page);
        Page<ScountMaterialEntity> page = new Query<ScountMaterialEntity>(params).getPage();

        return new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }
    @Override
    public PageUtils queryPageMaterial(Map<String, Object> params) {
//        Page<ScountMaterialEntity> page = this.selectPage(
//                new Query<ScountMaterialEntity>(params).getPage(),
//                new EntityWrapper<ScountMaterialEntity>()
//        );
//        return new PageUtils(page);
        Page<ScountMaterialEntity> page = new Query<ScountMaterialEntity>(params).getPage();

        return new PageUtils(page.setRecords(baseMapper.listByMaterial(page,params)));
    }


}
