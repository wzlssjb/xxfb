package com.shengvideo.modules.sys.service.impl;

import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.service.StbService;
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

import com.shengvideo.modules.sys.dao.SwitchSetDao;
import com.shengvideo.modules.sys.entity.SwitchSetEntity;
import com.shengvideo.modules.sys.service.SwitchSetService;


@Service("switchSetService")
public class SwitchSetServiceImpl extends ServiceImpl<SwitchSetDao, SwitchSetEntity> implements SwitchSetService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private StbService stbService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SwitchSetEntity> page = this.selectPage(
                new Query<SwitchSetEntity>(params).getPage(),
                new EntityWrapper<SwitchSetEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SwitchSetEntity> findByDeviceId(Long did,Integer ontime) {
        Map<String,Object> switchMap = new HashMap<>();
        switchMap.put("did",did);
        switchMap.put("ontime",1);
        return baseMapper.selectByDeviceOntime(switchMap);
    }

    @Override
    public void deleteByDeviceId(long did) {
       baseMapper.deleteByDevice(did);
    }

    @Override
    public void saveSwitchSet(Long[] dids, List<SwitchSetEntity> volSets) {
           for(Long id:dids){
              deleteByDeviceId(id);
              for(SwitchSetEntity swtich : volSets){
                  swtich.setDid(id);
                  insert(swtich);
              }
              //此处判断是否需要发通知终端
             List<SwitchSetEntity> switchSetEntities = findByDeviceId(id,1);
             if(switchSetEntities != null && switchSetEntities.size() > 0){
               nodeService.send(stbService.findById(id).getMac(),"switch_set",switchSetEntities);
             }
       }
    }
}
