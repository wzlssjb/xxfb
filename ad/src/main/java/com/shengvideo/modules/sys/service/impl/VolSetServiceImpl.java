package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.dao.VolSetDao;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.entity.VolSetEntity;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.VolSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("volSetService")
public class VolSetServiceImpl extends ServiceImpl<VolSetDao, VolSetEntity> implements VolSetService {
    @Autowired
    NodeService nodeService;
    @Autowired
    StbService stbService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<VolSetEntity> page = this.selectPage(
                new Query<VolSetEntity>(params).getPage(),
                new EntityWrapper<VolSetEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<VolSetEntity> selectByDevice(Long did){
        return baseMapper.selectByDevice(did);
    }

    @Override
    public void deleteByDevice(Long did){
        baseMapper.deleteByDevice(did);
    }

    @Override
    public void saveVolSets(Long[] dids, List<VolSetEntity> volSets){
        for (Long did: dids){
            deleteByDevice(did);
            for (VolSetEntity volSet: volSets) {
                volSet.setDid(did);
                volSet.setCreateDate(new Date());
                insert(volSet);
            }
            StbEntity stb = stbService.findById(did);
            nodeService.send(stb.getMac(),"set-vol-plan",volSets);
        }
    }

}
