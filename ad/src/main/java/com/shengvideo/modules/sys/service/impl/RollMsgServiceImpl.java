package com.shengvideo.modules.sys.service.impl;

import com.google.gson.Gson;
import com.shengvideo.common.utils.CronUtil;
import com.shengvideo.modules.job.entity.ScheduleJobEntity;
import com.shengvideo.modules.job.service.ScheduleJobService;
import com.shengvideo.modules.job.vo.TaskVo;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.StbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.RollMsgDao;
import com.shengvideo.modules.sys.entity.RollMsgEntity;
import com.shengvideo.modules.sys.service.RollMsgService;


@Service("rollMsgService")
public class RollMsgServiceImpl extends ServiceImpl<RollMsgDao, RollMsgEntity> implements RollMsgService {
    @Autowired
    NodeService nodeService;
    @Autowired
    StbService stbService;
    @Autowired
    ScheduleJobService scheduleJobService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RollMsgEntity> page = this.selectPage(
                new Query<RollMsgEntity>(params).getPage(),
                new EntityWrapper<RollMsgEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(Long[] dids,RollMsgEntity msg){
        msg.setCreateTime(new Date());
        if (msg.getStartTime() == null) {
            msg.setStartTime(msg.getCreateTime());
        }
        for (Long did: dids) {
            StbEntity stb = stbService.findById(did);
            msg.setDid(did);
            insert(msg);
            if (msg.getStartTime().getTime() <= new Date().getTime()) {
                nodeService.send(stb.getMac(),"add_roll_msg",msg);
            }else {
                Gson gson = new Gson();
                ScheduleJobEntity add = new ScheduleJobEntity();
                add.setBeanName("msgTask");
                add.setMethodName("add");
                add.setCronExpression(CronUtil.date2Cron(msg.getStartTime()));
                add.setParams("" + msg.getId());
                add.setType(0);
                scheduleJobService.save(add);
                TaskVo vo = new TaskVo();
                vo.setId(msg.getId());
                vo.setJbId(add.getJobId());
                add.setParams(gson.toJson(vo));
                scheduleJobService.update(add);
            }
        }
    }

}
