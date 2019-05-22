package com.shengvideo.modules.sys.service.impl;

import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.entity.PublishEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.PublishService;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.PublishDeviceDao;
import com.shengvideo.modules.sys.entity.PublishDeviceEntity;
import com.shengvideo.modules.sys.service.PublishDeviceService;


@Service("publishDeviceService")
public class PublishDeviceServiceImpl extends ServiceImpl<PublishDeviceDao, PublishDeviceEntity> implements PublishDeviceService {
    @Autowired
    StbService stbService;
    @Autowired
    NodeService nodeService;
    @Autowired
    PublishService publishService;
    @Autowired
    ThemeService themeService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PublishDeviceEntity> page = this.selectPage(
                new Query<PublishDeviceEntity>(params).getPage(),
                new EntityWrapper<PublishDeviceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PublishDeviceEntity> findByPid(Long pid){
        return baseMapper.findByPid(pid);
    }

    @Override
    public int selectOnCount(Long pid){
        return baseMapper.selectOnCount(pid);
    }

    @Override
    public void offLine(Long[] ids, Long pid){
        Map<String,Object> params = new HashMap<>();
        params.put("dids",ids);
        params.put("pid",pid);
        baseMapper.offLine(params);
        for (Long id :ids) {
            StbEntity stb = stbService.selectById(id);
            nodeService.send(stb.getMac(),"del-theme",pid);
        }
        if (selectOnCount(pid) == 0) {
            PublishEntity publish = publishService.selectById(pid);
            publish.setStatus(0);
            publishService.updateById(publish);
        }
    }

    @Override
    public void clearAll(Long[] dids){
        for (Long did: dids){
            baseMapper.clearAll(did);
            StbEntity device = stbService.findById(did);
            nodeService.send(device.getMac(),"theme",themeService.findPublishByDeviceOnTime(did));
        }
    }

    @Override
    public void clearMobile(Long[] dids){
        for (Long did: dids){
            baseMapper.clearMobile(did);
            StbEntity device = stbService.findById(did);
            nodeService.send(device.getMac(),"theme",themeService.findPublishByDeviceOnTime(did));
        }
    }

}
