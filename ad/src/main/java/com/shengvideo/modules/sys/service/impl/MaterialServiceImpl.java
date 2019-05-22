package com.shengvideo.modules.sys.service.impl;

import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.sys.dao.MaterialDao;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import com.shengvideo.modules.sys.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("materialService")
public class MaterialServiceImpl extends ServiceImpl<MaterialDao, MaterialEntity> implements MaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<MaterialEntity> page = this.selectPage(
//                new Query<MaterialEntity>(params).getPage(),
//                new EntityWrapper<MaterialEntity>()
//        );

        Page<MaterialEntity> page = new Query<MaterialEntity>(params).getPage();
        return new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }

    @Override
    public Long selectSize(Map map){
        return baseMapper.selectSize(map);
    }
    @Override
    public Long selectSizeByType(int type){
        Map map = new HashMap();
        map.put("type",type);
        return selectSize(map);
    }

}
