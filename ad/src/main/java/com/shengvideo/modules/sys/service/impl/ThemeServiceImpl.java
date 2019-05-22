package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.sys.dao.ThemeDao;
import com.shengvideo.modules.sys.entity.*;
import com.shengvideo.modules.sys.service.*;
import com.shengvideo.modules.sys.vo.PublishVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Service("themeService")
public class ThemeServiceImpl extends ServiceImpl<ThemeDao, ThemeEntity> implements ThemeService {

    @Autowired
    private ThemePageService pageService;
    @Autowired
    private ElementService elementService;
    @Autowired
    private ThemeUseMtService useMtService;
    @Autowired
    private PublishService publishService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        Page<ThemeEntity> page = this.selectPage(
                new Query<ThemeEntity>(params).getPage(),
                new EntityWrapper<ThemeEntity>()
                        .like(StringUtils.isNotBlank(key),"title",key)
                        .eq("type",1)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryTempPage(Map<String, Object> params) {
//        Page<MaterialEntity> page = this.selectPage(
//                new Query<MaterialEntity>(params).getPage(),
//                new EntityWrapper<MaterialEntity>()
//        );

        Page<ThemeEntity> page = new Query<ThemeEntity>(params).getPage();
        return new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }

    @Override
    public ThemeEntity findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<ThemeEntity> findByDevices(Long[] ids){
        return baseMapper.findByDevices(ids);
    }

    @Override
    public List<ThemeEntity> findByPublish(Long pid) {
        return baseMapper.findByPublish(pid);
    }

    @Override
    public List<ThemeEntity> findByPublishAndDevice(Long pid, Long did) {
        return findByPublishAndDevice(pid,did,null);
    }

    @Override
    public List<ThemeEntity> findByPublishAndDeviceNormal(Long pid, Long did) {
        return findByPublishAndDevice(pid,did,true);
    }

    @Override
    public List<ThemeEntity> findByPublishAndDevice(Long pid, Long did, Boolean status) {
        Map<String,Object> params = new HashMap<>();
        params.put("did",did);
        params.put("pid",pid);
        params.put("status",status);
        return baseMapper.findByPublishAndDevice(params);
    }

    @Transactional
    @Override
    public boolean insert(ThemeEntity theme){
        boolean done = super.insert(theme);
        insertPagesAndElements(theme);
        insertUseMt(theme);
        return done;
    }

    @Transactional
    @Override
    public boolean updateById(ThemeEntity theme){
        boolean done = super.updateById(theme);
        delPagesAndElements(findById(theme.getId()));
        insertPagesAndElements(theme);
        useMtService.delByTheme(theme.getId());
        insertUseMt(theme);
        return done;
    }

    @Override
    public void updateInfo(ThemeEntity theme){
        baseMapper.updateById(theme);
    }

    private void insertPagesAndElements(ThemeEntity theme){
        for (ThemePageEntity page : theme.getPages()){
            page.setTid(theme.getId());
            pageService.insert(page);
            for (ElementEntity element : page.getElements()){
                element.setPid(page.getId());
                elementService.insert(element);
            }
        }
    }


    private void insertUseMt(ThemeEntity theme){
        List<ThemeUseMtEntity> mtEntities = new ArrayList<>();
        for (ThemePageEntity page : theme.getPages()){
            for (ElementEntity element : page.getElements()){
                if (element.getMid() != null){
                    ThemeUseMtEntity useMt = new ThemeUseMtEntity();
                    useMt.setTid(theme.getId());
                    useMt.setMid(element.getMid());
                    mtEntities.add(useMt);
                }
            }
            if (page.getMid() != null){
                ThemeUseMtEntity useMt = new ThemeUseMtEntity();
                useMt.setMid(page.getMid());
                useMt.setTid(theme.getId());
                mtEntities.add(useMt);
            }
        }
        if(!mtEntities.isEmpty()){
            useMtService.insertBatch(mtEntities);
        }
    }


    private void delPagesAndElements(ThemeEntity theme){
        for (ThemePageEntity page : theme.getPages()){
            elementService.delByPid(page.getId());
        }
        pageService.delByTid(theme.getId());
    }


    @Override
    public List<ThemeEntity> findThemeByDeviceOnTime(Long did) {
        List<ThemeEntity> list = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();
        params.put("date",now);
        params.put("time",time);
        params.put("interCut",true);
        params.put("did",did);
        List<PublishEntity> publishs = publishService.findByNow(params); //查找插播节目
        if (publishs.size() == 0) {   // 无插播则查找普通节目
            params.put("interCut",false);
            publishs = publishService.findByNow(params);
        }
        for (PublishEntity publish:publishs){
            list.addAll(findByPublishAndDeviceNormal(publish.getId(),did));
        }
        return list;
    }

    @Override
    public List<PublishVo> findPublishByDeviceOnTime(Long did) {
        return findPublishByDeviceAndRangeTime(did,null,null);
    }

    @Override
    public List<PublishVo> findPublishByDeviceAndRangeTime(Long did, Date startTime, Date endTime) {
        List<PublishVo> publishVos = new ArrayList<>();
        List<PublishEntity> publishs = publishService.findInterCut(did,startTime,endTime); //查找插播节目
        if (publishs.size() == 0) {   // 无插播则查找普通节目
            publishs = publishService.findnormal(did,startTime,endTime);
            for (PublishEntity publish:publishs){
                PublishVo publishVo = new PublishVo();
                publishVo.setId(publish.getId());
                publishVo.setThemes(findByPublishAndDevice(publish.getId(),did));
                publishVo.setInterCut(publish.getInterCut());
                if (publishVo.getThemes().size() > 0) {
                    publishVos.add(publishVo);
                }
            }
        }else {
            PublishVo publishVo = new PublishVo();
            publishVo.setId(publishs.get(0).getId());
            publishVo.setThemes(findByPublishAndDeviceNormal(publishs.get(0).getId(),did));
            publishVo.setInterCut(true);
            if (publishVo.getThemes().size() > 0) {
                publishVos.add(publishVo);
            }
        }
        return publishVos;
    }

}
