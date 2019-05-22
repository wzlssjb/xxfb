package com.shengvideo.modules.job.task;

import com.shengvideo.common.exception.RRException;
import com.shengvideo.config.AuthoConfig;
import com.shengvideo.modules.sys.service.AuthoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("autoTask")
public class AutoTask {
    @Autowired
    private AuthoService authoService;
    @Autowired
    AuthoConfig authoConfig;

    public void auto(){
        try {
            authoConfig.setLose(authoService.autho());
        }catch (RRException e){
            authoConfig.setLose(false);  //授权失败
            authoConfig.setMsg(e.getMsg());
        }
    }
}
