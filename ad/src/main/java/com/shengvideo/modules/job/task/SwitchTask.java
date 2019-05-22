package com.shengvideo.modules.job.task;

import com.shengvideo.common.utils.WZDateUtil;
import com.shengvideo.modules.job.service.ScheduleJobService;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.sys.entity.PublishEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.entity.SwitchSetEntity;
import com.shengvideo.modules.sys.service.PublishService;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.SwitchSetService;
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

@Component("SwitchTask")
public class SwitchTask {
    @Autowired
    private StbService stbService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private SwitchSetService switchSetService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public void checkDate(){
      List<StbEntity> stbs = stbService.selectByMap(null);
      for (StbEntity stb:stbs) {
          List<SwitchSetEntity> entities = switchSetService.findByDeviceId(stb.getId(),1);
          if(entities != null && entities.size() > 0){
              nodeService.send(stb.getMac(),"switch_set",entities);
          }
      }
    }

}
