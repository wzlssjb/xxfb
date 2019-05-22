package com.shengvideo.modules.job.task;

import com.google.gson.Gson;
import com.shengvideo.modules.job.service.ScheduleJobService;
import com.shengvideo.modules.job.vo.TaskVo;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.entity.RollMsgEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.RollMsgService;
import com.shengvideo.modules.sys.service.StbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("msgTask")
public class MsgTask {
    @Autowired
    private RollMsgService rollMsgService;
    @Autowired
    private StbService stbService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public void add(String params){
        logger.info(params);
        Gson gson = new Gson();
        TaskVo taskVo = gson.fromJson(params,TaskVo.class);
        RollMsgEntity msg = rollMsgService.selectById(taskVo.getId());
        StbEntity stb = stbService.findById(msg.getId());
        nodeService.send(stb.getMac(),"add_roll_msg",msg);
        Long [] ids = {taskVo.getJbId()};
        scheduleJobService.deleteBatch(ids);
    }

}
