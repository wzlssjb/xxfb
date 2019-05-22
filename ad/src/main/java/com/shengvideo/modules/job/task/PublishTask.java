package com.shengvideo.modules.job.task;

import com.shengvideo.common.utils.WZDateUtil;
import com.shengvideo.modules.job.service.ScheduleJobService;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.entity.PublishEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.PublishService;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.ThemeService;
import com.shengvideo.modules.sys.vo.PublishVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("publishTask")
public class PublishTask {
    @Autowired
    private PublishService publishService;
    @Autowired
    private StbService stbService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public void add(String params){
        if (StringUtils.isBlank(params)) {
            logger.error("参数有误！！");
        }
        Long pid = Long.parseLong(params);
        PublishEntity publish = publishService.selectById(pid);
        if (publish.getStatus() != 1) {  //检查发布是否在线
            return;
        }
        if (!checkDate(publish)) {
            scheduleJobService.stopByPublish(pid);
            return;
        }
        List<StbEntity> stbs = stbService.findByPublish(publish.getId());
        PublishVo publishVo = new PublishVo();
        publishVo.setId(publish.getId());
        publishVo.setInterCut(publish.getInterCut());

        for (StbEntity stb : stbs) {
            if (checkInterCut(stb,publish)){
                continue;
            }
            publishVo.setThemes(themeService.findByPublishAndDeviceNormal(publish.getId(),stb.getId()));
            nodeService.send(stb.getMac(),"add-theme",publishVo);
        }

    }

    public void del(String params){
        if (StringUtils.isBlank(params)) {
            logger.error("参数有误！！");
        }
        Long pid = Long.parseLong(params);
        List<StbEntity> stbs = stbService.findByPublish(pid);
        for (StbEntity stb : stbs) {
            nodeService.send(stb.getMac(),"del-theme",pid);
        }
    }

    private boolean checkDate(PublishEntity publish) {  //是否在有效期
        if (WZDateUtil.compareDate(new Date(),publish.getEndTime(), Calendar.DATE) > 0) {
            return false;
        }
        return true;
    }

    private boolean checkInterCut(StbEntity stb,PublishEntity publish) {  //是否有插播 如果本身是插播 且时间为最晚的 则无视插播
        List<PublishEntity> publishs = publishService.findInterCut(stb.getId());
        if (publish.getInterCut()) {
            if (publishs.get(0).getId().intValue() != publish.getId()) {
                return true;
            }
        }else {
            if (publishs.size() > 0) {
                return true;
            }
        }
        return false;
    }

}
