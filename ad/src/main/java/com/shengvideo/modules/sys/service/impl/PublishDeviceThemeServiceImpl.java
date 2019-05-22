package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.dao.PublishDeviceThemeDao;
import com.shengvideo.modules.sys.entity.PublishDeviceThemeEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.PublishDeviceService;
import com.shengvideo.modules.sys.service.PublishDeviceThemeService;
import com.shengvideo.modules.sys.service.StbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("publishDeviceThemeService")
public class PublishDeviceThemeServiceImpl extends ServiceImpl<PublishDeviceThemeDao, PublishDeviceThemeEntity> implements PublishDeviceThemeService {
    @Autowired
    StbService stbService;

    @Autowired
    NodeService nodeService;
    @Autowired
    PublishDeviceService publishDeviceService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PublishDeviceThemeEntity> page = this.selectPage(
                new Query<PublishDeviceThemeEntity>(params).getPage(),
                new EntityWrapper<PublishDeviceThemeEntity>()
                        .setSqlSelect("id,tid,status,synchronize,did,pid," +
                                "(select name from stb where id = did) as dName," +
                                "(select title from theme where id = tid) as tName")
                        .eq(params.get("pid")!=null,"pid",params.get("pid"))

        );

        return new PageUtils(page);
    }

    @Override
    public void subPlay(Long[] dids, Long[] tids){
        Map<String,Object> param = new HashMap<>();
        param.put("did",dids);
        param.put("tid",tids);
        for (Long did: dids){
            StbEntity device = stbService.selectById(did);
            nodeService.subPlay(device.getMac(),tids);
        }
        baseMapper.subPlay(param);
        List<Long> pidList = pidList(dids,tids);
        for (Long pid:pidList){
            for (Long did:dids) {
                if (selectOnCount(pid,did) == 0) {
                    Long[] ids = new Long[1];
                    ids[0] = did;
                    publishDeviceService.offLine(ids,pid);
                }
            }
        }
    }

    @Override
    public List<Long> pidList(Long[] dids, Long[] tids){
        Map<String,Object> param = new HashMap<>();
        param.put("did",dids);
        param.put("tid",tids);
        return baseMapper.pidList(param);
    }

    @Override
    public int selectOnCount(Long pid, Long did){
        return baseMapper.selectOnCount(pid,did);
    }


}
