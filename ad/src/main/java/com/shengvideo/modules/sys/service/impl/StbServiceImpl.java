package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.Constant;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.dao.StbDao;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.MaterialService;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.SysConfigService;
import com.shengvideo.modules.sys.vo.OpVo;
import com.shengvideo.modules.sys.vo.RemoteInstallVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("stbService")
public class StbServiceImpl extends ServiceImpl<StbDao, StbEntity> implements StbService {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<StbEntity> page = this.selectPage(
//                new Query<StbEntity>(params).getPage(),
//                new EntityWrapper<StbEntity>()
//        );
        Page<StbEntity> page = new Query<StbEntity>(params).getPage();
        return new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }

    @Override
    public StbEntity findByMAC(Map<String, Object> params) {
        return baseMapper.findByMac(params);
    }

    @Override
    public StbEntity findById(Long id) {
        return baseMapper.findById(id);
    }


    @Override
    public List<StbEntity> findByPublish(Long pid){
        return baseMapper.findByPublish(pid);
    }

    @Override
    public boolean updateStb(StbEntity stbEntity) {
        return retBool(baseMapper.updateById(stbEntity));
    }

    @Override
    public void updateInfo(StbEntity stbEntity) {
        baseMapper.updateById(stbEntity);
    }

    @Override
    public void remoteInstall( RemoteInstallVo vo) {
        MaterialEntity material = materialService.selectById(vo.getMids().get(0));
        String basePath =sysConfigService.getValue(Constant.ROOT_PATH);
        nodeService.remoteInstall(vo.getMacs(),basePath+material.getFilePath(),material.getPackageName(),vo.getInstallType());


    }

    @Override
    public List<StbEntity> selectByRouteOnLine(Integer rid){
        Map map = new HashMap();
        map.put("route_id", rid);
        map.put("switchStatus", true);
        map.put("onlineStatus", true);
        Page<StbEntity> page = new Query<StbEntity>(map).getPage();
        return baseMapper.list(page,map);
    }

    @Override
    public int selectCountAll(){
        Map map = new HashMap();
        return baseMapper.selectCount(map);
    }

    @Override
    public int selectCountByOnline(boolean online){
        Map map = new HashMap();
        map.put("online_status",online);
        return baseMapper.selectCount(map);
    }

    @Override
    public int selectCountBySwitch(boolean switchStatus){
        Map map = new HashMap();
        map.put("switch_status",switchStatus);
        return baseMapper.selectCount(map);
    }

    @Override
    public int selectCountByGroup(Long gid){
        return baseMapper.selectCountByGroup(gid);
    }

    @Override
    public void updateVol(Long[] dids, int vol){
        Map<String,Object> params = new HashMap<>();
        params.put("dids",dids);
        params.put("vol",vol);
        for (Long did:dids) {
            StbEntity stb = findById(did);
            nodeService.send(stb.getMac(),"set-vol",vol);
        }
        baseMapper.updateVol(params);
    }

    @Override
    public void initOnline() {
        baseMapper.initOnline();
    }

    @Override
    public List<OpVo> resolution(){
        return baseMapper.resolution();
    }

    @Override
    public void remoteBoot(RemoteInstallVo vo) {

           nodeService.reboot(vo.getMacs(),vo.getInstallType());
        }

    @Override
    public void updateDeleteGroupId(Long pid) {
        baseMapper.updateDeleteGroupId(pid);
    }
}
