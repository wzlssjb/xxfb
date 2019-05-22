package com.shengvideo.modules.sys.service.impl;

import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.sys.dao.MaterialClassDao;
import com.shengvideo.modules.sys.entity.MaterialClassEntity;
import com.shengvideo.modules.sys.service.MaterialClassService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("materialClassService")
public class MaterialClassServiceImpl extends ServiceImpl<MaterialClassDao, MaterialClassEntity> implements MaterialClassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MaterialClassEntity> page = this.selectPage(
                new Query<MaterialClassEntity>(params).getPage(),
                new EntityWrapper<MaterialClassEntity>()
        );

        return new PageUtils(page);
    }

}
