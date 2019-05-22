package com.shengvideo.modules.push.service;

import com.shengvideo.modules.push.vo.Client;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {
    @Value("${spring.redis.open}")
    private boolean redisOpen;
    @Autowired
    ClientManage clientManage;

    @Autowired
    private SysConfigService configService;

    @Autowired
    private StbService stbService;

    Logger log = LoggerFactory.getLogger(NodeService.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void send(String mac,String event,Object data){
        Client client = clientManage.getByMac(mac);
        if (client == null){
            log.info("发布【" + event + "】失败，设备不在线");
            return;
        }
        client.getClient().sendEvent(event,data);
    }



    //设置音量
    public void setVoice(List<String> macs,Integer voice){
        for(String mac:macs){
            Client tv = clientManage.getByMac(mac);
            if (tv != null){
                tv.getClient().sendEvent("setVoice",voice);
                log.info("设置音量："+voice+" mac:" + mac);
            }
        }
    }
    //重启，关机
    public void reboot(List<String> macs,int type){
        if(type==1){
            for(String mac :macs){
                Client tv = clientManage.getByMac(mac);
                if (tv != null){
                       tv.getClient().sendEvent("reboot");
                       log.info("终端重启： mac:" + mac);
                }
            }
        }else{
            for(String mac :macs){
                Client tv = clientManage.getByMac(mac);
                if (tv != null){
                    tv.getClient().sendEvent("shutDown");
                    log.info("终端关机： mac:" + mac);
                }
            }
        }



    }
    //远程安装
    public void remoteInstall( List<String> macs,String apkLink,String packageName,int installType){
        if(installType == 1){
            for(String mac:macs){
                Client tv = clientManage.getByMac(mac);
                if (tv != null){
                    tv.getClient().sendEvent("install",apkLink,packageName);
                    log.info("安装软件包名："+packageName+" mac:" + mac);

                }
            }
        }else{
            for(String mac:macs){
                Client tv = clientManage.getByMac(mac);
                if (tv != null){
                    tv.getClient().sendEvent("uninstall",apkLink,packageName);
                    log.info("卸载软件包名："+packageName+" mac:" + mac);

                }
            }
        }


    }


    //减播
    public void subPlay(String mac,Long[] tids){
        Client tv = clientManage.getByMac(mac);
        if (tv != null){
            tv.getClient().sendEvent("sub-play",tids);
        }
    }
}
