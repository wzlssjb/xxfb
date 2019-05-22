package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.sys.dao.ElementDao;
import com.shengvideo.modules.sys.entity.ElementEntity;
import com.shengvideo.modules.sys.service.ElementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("elementService")
public class ElementServiceImpl extends ServiceImpl<ElementDao, ElementEntity> implements ElementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ElementEntity> page = this.selectPage(
                new Query<ElementEntity>(params).getPage(),
                new EntityWrapper<ElementEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ElementEntity> findByPid(Long tid){
        return baseMapper.findByPid(tid);
    }

    @Override
    public void delByPid(Long tid){
        baseMapper.delByPid(tid);
    }

}
