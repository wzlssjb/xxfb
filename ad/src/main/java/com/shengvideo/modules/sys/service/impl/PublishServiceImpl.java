package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.CronUtil;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.common.utils.WZDateUtil;
import com.shengvideo.modules.job.entity.ScheduleJobEntity;
import com.shengvideo.modules.job.service.ScheduleJobService;
import com.shengvideo.modules.sys.dao.PublishDao;
import com.shengvideo.modules.sys.entity.*;
import com.shengvideo.modules.sys.form.AddPlayForm;
import com.shengvideo.modules.sys.form.MobilePushForm;
import com.shengvideo.modules.sys.service.*;
import com.shengvideo.modules.sys.vo.TimeRangOnPlayTheme;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.shengvideo.modules.sys.entity.MaterialEntity.IMG;


@Service("publishService")
public class PublishServiceImpl extends ServiceImpl<PublishDao, PublishEntity> implements PublishService {
    @Autowired
    PublishPlayTimeService playTimeService;
    @Autowired
    PublishDeviceService deviceService;
    @Autowired
    PublishThemeService publishThemeService;
    @Autowired
    ThemeService themeService;
    @Autowired
    ScheduleJobService scheduleJobService;
    @Autowired
    PublishDeviceThemeService publishDeviceThemeService;
    @Autowired
    StbService stbService;
    @Autowired
    SysConfigService configService;
    @Autowired
    MaterialService materialService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PublishEntity> page = this.selectPage(
                new Query<PublishEntity>(params).getPage(),
                new EntityWrapper<PublishEntity>()
                        .like(StringUtils.isNotBlank(params.get("key").toString()),"name",params.get("key").toString())
        );

        return new PageUtils(page);
    }

    @Override
    public boolean insert(PublishEntity model) {
        model.setCreateTime(new Date());
        model.setStatus(1);

        boolean status = super.insert(model);
        if (!status){
            return false;
        }

        for (StbEntity stb:model.getDevices()) {
            PublishDeviceEntity publishDevice = new PublishDeviceEntity();
            publishDevice.setPid(model.getId());
            publishDevice.setDid(stb.getId());
            deviceService.insert(publishDevice);

            for (ThemeEntity theme:model.getThemes()) {
                PublishThemeEntity publishTheme = new PublishThemeEntity();
                publishTheme.setPid(model.getId());
                publishTheme.setTid(theme.getId());
                publishThemeService.insert(publishTheme);
                PublishDeviceThemeEntity publishDeviceTheme = new PublishDeviceThemeEntity();
                publishDeviceTheme.setDid(stb.getId());
                publishDeviceTheme.setPid(model.getId());
                publishDeviceTheme.setTid(theme.getId());
                publishDeviceThemeService.insert(publishDeviceTheme);
            }
        }
        if (model.getPlayMode() == 2) {  // 2 自定义播放模式
            String week = null;
            if (model.getCycleMode() == 2) {
                week = model.getCycleWeek();
            }
            for (PublishPlayTimeEntity playTime: model.getPlayTimes()){
                playTime.setPid(model.getId());
                playTimeService.insert(playTime);
                ScheduleJobEntity add = new ScheduleJobEntity();
                add.setBeanName("publishTask");
                add.setMethodName("add");
                add.setCronExpression(CronUtil.time2Cron(playTime.getStartTime(),week));
                add.setParams("" + model.getId());
                add.setType(0);

                ScheduleJobEntity del = new ScheduleJobEntity();
                del.setBeanName("publishTask");
                del.setMethodName("del");
                del.setCronExpression(CronUtil.time2Cron(playTime.getEndTime(),week));
                del.setParams("" + model.getId());
                del.setType(0);
                scheduleJobService.save(add);
                scheduleJobService.save(del);
                logger.info("当前" + WZDateUtil.compareTime(new Date(),playTime.getStartTime(),Calendar.SECOND));
                if (checkOnDate(model)) {
                    if (WZDateUtil.compareTime(new Date(),playTime.getStartTime(),Calendar.SECOND) >=0
                    && WZDateUtil.compareTime(new Date(),playTime.getEndTime(),Calendar.SECOND)<=0){
                        scheduleJobService.run(add.getJobId());
                    }
                }
            }
        }else {
            PublishPlayTimeEntity playTime = new PublishPlayTimeEntity();
            playTime.setPid(model.getId());
            try {
                playTime.setStartTime(WZDateUtil.parseDate("00:00:00","HH:mm:ss"));
                playTime.setEndTime(WZDateUtil.parseDate("23:59:59","HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            playTimeService.insert(playTime);
            ScheduleJobEntity add = new ScheduleJobEntity();
            add.setBeanName("publishTask");
            add.setMethodName("add");
            add.setCronExpression(CronUtil.date2Cron(model.getStartTime()));
            add.setParams("" + model.getId());
            add.setType(0);
            ScheduleJobEntity del = new ScheduleJobEntity();
            del.setBeanName("publishTask");
            del.setMethodName("del");
            del.setCronExpression(CronUtil.date2Cron(model.getEndTime()));
            del.setParams("" + model.getId());
            del.setType(0);
            scheduleJobService.save(add);
            scheduleJobService.save(del);
            if (checkOnDate(model)) {
                scheduleJobService.run(add.getJobId());
            }
        }
        return true;
    }

    @Override
    public List<PublishEntity> findByNow(Map<String, Object> params){
        return baseMapper.findByNow(params);
    }

    @Override
    public List<PublishEntity> findInterCut(Long did){
        return findInterCut(did,null,null);
    }

    @Override
    public List<PublishEntity> findnormal(Long did){
        return findnormal(did,null,null);
    }

    @Override
    public List<PublishEntity> findInterCut(Long did, Date startTime, Date endTime){
        LocalDate now = LocalDate.now();
        Map<String,Object> params = new HashMap<>();
        LocalTime time = LocalTime.now();
        params.put("date",now);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        if (startTime == null) {
            params.put("time",time);
        }
        params.put("interCut",true);
        params.put("did",did);
        params.put("sidx","create_time");
        params.put("order","desc");
        return findByNow(params);
    }

    @Override
    public List<PublishEntity> findnormal(Long did, Date startTime, Date endTime){
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();
        Map<String,Object> params = new HashMap<>();
        params.put("date",now);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        if (startTime == null) {
            params.put("time",time);
        }
        params.put("interCut",false);
        params.put("did",did);
        params.put("sidx","create_time");
        params.put("order","asc");
        return findByNow(params);
    }

    @Override
    public boolean checkOnDate(PublishEntity publish){
        if (WZDateUtil.compareDate(new Date(),publish.getStartTime(), Calendar.DATE) >= 0&&
                WZDateUtil.compareDate(new Date(),publish.getEndTime(), Calendar.DATE) <= 0){
            if (publish.getPlayMode() == 1) {
                return true;
            }else {
                if (publish.getCycleMode() == 1) {
                    return true;
                }else if (CronUtil.toUKWeek(publish.getCycleWeek()).indexOf(WZDateUtil.getWeek() + "") > -1){
                     return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteByIds(List<Long> idList) {
        for (Long id:idList){
            deleteById(id);
        }
        return true;
    }

    @Override
    public boolean  deleteById(Long id){
        scheduleJobService.stopByPublish(id);
        return super.deleteById(id);
    }

    @Override
    public void addPlay(AddPlayForm form, Long uid){
        StbEntity firstDevice = stbService.selectById(form.getDids()[0]);
        List<StbEntity> devices = ids2Devices(form.getDids());
        for (Long mid : form.getMids()){
            MaterialEntity material = materialService.selectById(mid);
            PublishEntity publishEntity = new PublishEntity();
            publishEntity.setUid(uid);
            publishEntity.setPlayMode(1);
            publishEntity.setStartTime(form.getStartTime());
            publishEntity.setEndTime(form.getEndTime());
            publishEntity.setName("加播" + material.getName());
            publishEntity.setDevices(devices);
            List<ThemeEntity> themes = new ArrayList<>();
            themes.add(material2Theme(material,firstDevice.getWidth(),firstDevice.getHeight(),null));
            publishEntity.setThemes(themes);
            insert(publishEntity);
        }

    }

    @Override
    public void mobilePush(MobilePushForm form, Long uid){
        StbEntity firstDevice = stbService.selectById(form.getDids()[0]);
        List<StbEntity> devices = ids2Devices(form.getDids());
        MaterialEntity material = materialService.selectById(form.getMid());
        PublishEntity publishEntity = new PublishEntity();
        publishEntity.setUid(uid);
        publishEntity.setPlayMode(1);
        publishEntity.setStartTime(new Date());
        publishEntity.setEndTime(WZDateUtil.addYear(new Date(),3));
        publishEntity.setName(material.getName());
        publishEntity.setDevices(devices);
        List<ThemeEntity> themes = new ArrayList<>();
        ThemeEntity theme = material2Theme(material,firstDevice.getWidth(),firstDevice.getHeight(),form.getStretch());
        theme.getPages().get(0).setDuration(form.getDuration());
        themeService.updateInfo(theme);
        themes.add(theme);
        publishEntity.setThemes(themes);
        publishEntity.setType(2);
        insert(publishEntity);

    }


    @Override
    public void pushText (ElementEntity element, Long[] dids, Long uid) {
        element.setType("text");
        StbEntity firstDevice = stbService.selectById(dids[0]);
        String name ="全屏字幕" +  new Date().getTime();
        List<StbEntity> devices = ids2Devices(dids);
        PublishEntity publishEntity = new PublishEntity();
        publishEntity.setUid(uid);
        publishEntity.setPlayMode(1);
        publishEntity.setStartTime(new Date());
        publishEntity.setEndTime(WZDateUtil.addYear(new Date(),3));
        publishEntity.setName(name);
        publishEntity.setDevices(devices);
        List<ThemeEntity> themes = new ArrayList<>();
        List<ElementEntity> elements = new ArrayList<>();
        elements.add(element);
        ThemePageEntity page = new ThemePageEntity();
        page.setElements(elements);
        page.setDuration(30);
        List<ThemePageEntity> pages = new ArrayList<>();
        pages.add(page);
        ThemeEntity theme = new ThemeEntity();
        theme.setPages(pages);
        theme.setType(1);
        theme.setTitle(name);
        theme.setCanvasHeight(firstDevice.getHeight());
        theme.setCanvasWidth(firstDevice.getWidth());
        themeService.insert(theme);
        themes.add(theme);
        publishEntity.setThemes(themes);
        publishEntity.setType(2);
        insert(publishEntity);
    }

    @Override
    public List<TimeRangOnPlayTheme> findTodayTimeRange(Long did){
        List<TimeRangOnPlayTheme> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        List<Date> times = findTimes(date,did);
        for (int i = 0; i < times.size() - 1; i++){
            TimeRangOnPlayTheme timeRangOnPlayTheme = new TimeRangOnPlayTheme();
            timeRangOnPlayTheme.setStartTime(times.get(i));
            timeRangOnPlayTheme.setEndTime(times.get(i+1));
            timeRangOnPlayTheme.setPublishs(themeService
                    .findPublishByDeviceAndRangeTime(did,timeRangOnPlayTheme.getStartTime(),timeRangOnPlayTheme.getEndTime()));

            if (WZDateUtil.compareTime(new Date(),timeRangOnPlayTheme.getStartTime(),Calendar.SECOND) >=0
                    && WZDateUtil.compareTime(new Date(),timeRangOnPlayTheme.getEndTime(),Calendar.SECOND)<0){
                timeRangOnPlayTheme.setOn(true);
            }
            if (timeRangOnPlayTheme.getPublishs().size() > 0){
                list.add(timeRangOnPlayTheme);
            }

        }

        return list;
    }

    @Override
    public List<Date> findTimes(LocalDate date, Long did){
        Map<String,Object> params = new HashMap<>();
        params.put("date",date);
        params.put("did",did);
        return baseMapper.findTimes(params);
    }

    private ThemeEntity material2Theme(MaterialEntity material,int width, int height, Integer stretch){
        ThemeEntity theme = new ThemeEntity();
        theme.setTitle("节目" + material.getName());
        theme.setCanvasWidth(width);
        theme.setDescription("节目加播");
        theme.setCanvasHeight(height);
        theme.setType(1);
        ThemePageEntity page = new ThemePageEntity();
        page.setDuration(1000);
        page.setMid(material.getId());

        ElementEntity element = new ElementEntity();
        if (stretch == null) {
            stretch = 1;
        }
        element.setStretch(stretch);
        element.setTop(0);
        element.setLeft(0);
        element.setMid(material.getId());
        if (IMG ==  material.getType()) {
            element.setImgSrc(configService.getValue("source_server") + material.getFilePath());
            element.setWidth(material.getWidth());
            element.setHeight(material.getHeight());
        }else {
            element.setVideoSrc(configService.getValue("source_server") + material.getFilePath());
            element.setWidth(width);
            element.setHeight(height);
        }
        switch (material.getType()) {
            case 2:
                element.setType("pic");
                break;
            case 1:
                element.setType("video");
                break;
            case 3:
                element.setType("doc");
                break;
            case 5:
                element.setType("anim");
                break;
        }
        List<ElementEntity> elementEntities = new ArrayList<>();
        elementEntities.add(element);
        page.setElements(elementEntities);
        List<ThemePageEntity> pages = new ArrayList<>();
        pages.add(page);
        theme.setPages(pages);
        themeService.insert(theme);
        return theme;
    }
    private List<StbEntity> ids2Devices(Long[] dids){
        List<StbEntity> list = new ArrayList<>();
        for (Long id:dids){
            StbEntity stb = new StbEntity();
            stb.setId(id);
            list.add(stb);
        }
        return list;
    }
}
