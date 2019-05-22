package com.shengvideo.modules.push.server.handler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.google.gson.Gson;
import com.shengvideo.config.AuthoConfig;
import com.shengvideo.modules.push.service.ClientManage;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.push.vo.Client;
import com.shengvideo.modules.push.vo.StbLog;
import com.shengvideo.modules.push.vo.StbSet;
import com.shengvideo.modules.sys.entity.SlogEntity;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.entity.SwitchSetEntity;
import com.shengvideo.modules.sys.entity.ThemeEntity;
import com.shengvideo.modules.sys.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Component
public class NodeEventHandler {
    private Logger logger = LoggerFactory.getLogger(NodeEventHandler.class);
    @Autowired
    protected SocketIOServer server;

    @Autowired
    private ClientManage clientManage;

    @Autowired
    private StbService stbService;

    @Autowired
    private ThemeService themeService;
    @Autowired
    private SlogService slogService;

    @Autowired
    private ScountThemeService scountThemeService;

    @Autowired
    AuthoConfig authoConfig;

    @Autowired
    private SwitchSetService switchSetService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    SysConfigService configService;
    @OnConnect
    public void  onConnect(SocketIOClient client){
        String ip = client.getHandshakeData().getAddress().toString();
        logger.info("终端连接   ip [" + ip.substring(1) + "]");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        Client c = clientManage.getClient(client);
        if(c == null){
            return;
        }
        Map map = new HashMap();
        map.put("mac",c.getId());
        StbEntity stbEntity = stbService.findByMAC(map);
        if(stbEntity == null){
            return ;
        }
        //保存终端下线日志
        SlogEntity slogEntity = new SlogEntity(stbEntity.getId(),StbLog.STATU_OFFILINE,StbLog.MSG_OFFILINE,new Date());
        slogService.insert(slogEntity);
        clientManage.delClient(client);
    }

    @Transactional
    @OnEvent(value = "register")
    public void register(SocketIOClient client, AckRequest request, String json) {
        Gson gson = new Gson();
        StbSet set = gson.fromJson(json, StbSet.class);
        String ip = client.getHandshakeData().getAddress().toString();
        logger.info("终端注册   ip [" + ip.substring(1) + "] mac[" + set.getMac());
        Map map = new HashMap();
        map.put("mac",set.getMac());
        StbEntity stb = stbService.findByMAC(map);
        if(stb == null ){
            client.disconnect();   //没有找到此设备，请求链接直接下线
            return ;
        }
        //后台未授权
        if(!authoConfig.isLose()){
            client.sendEvent("autho_mess", authoConfig.getMsg() != null ?authoConfig.getMsg() : "系统未授权，请联系管理员");
            return;
        }
        if(stb.getSwitchStatus() != 1){  //终端设备关 ,给提示并且强行退出
            client.sendEvent("switch_status", "该终端出于关闭状态，请联系后台管理员！");
            client.disconnect();
            return;
        }
        //开始修改终端基本信息（IP，分辨率，终端版本号，）
        Client old =clientManage.getByMac(set.getMac());
        if (old != null){
            clientManage.delClient(old.getClient());
        }
        stb.setIpaddress(ip.substring(1,ip.lastIndexOf(":"))); //设置IP
        if(set.getVersion() != null){
            stb.setVersion(set.getVersion());   //终端版本号
        }
        if(set.getWidth() != null){
            stb.setWidth(set.getWidth());  //分辨率
            stb.setHeight(set.getHeight());
        }
        if(set.getRam() != null){
            stb.setRam(set.getRam());          //内存
        }
        stb.setOnlineStatus(StbEntity.ONLINE_TRUE);
        stbService.updateById(stb);  //更新状态
        addClinet(client,set.getMac());

        //保存终端日志
        SlogEntity slogEntity = new SlogEntity(stb.getId(),StbLog.STATU_ONLINE,StbLog.MSG_ONLINE,new Date());
        slogService.insert(slogEntity);
//      终端节目广告信息
        client.sendEvent("theme", themeService.findPublishByDeviceOnTime(stb.getId()));
        //终端主题
        //基本信息 (声音，版本号，内存情况)
        client.sendEvent("basic_masseage",stb);
        //开关机消息
        List<SwitchSetEntity> switchSetEntities = switchSetService.findByDeviceId(stb.getId(),1);
       if(switchSetEntities != null && switchSetEntities.size() > 0){
           nodeService.send(stb.getMac(),"switch_set",switchSetEntities);
       }
    }

    //播放节目消息
//    @OnEvent("playPublish")
//    public void playPublish(SocketIOClient client,AckRequest request,long tid){
//        Client c = clientManage.getClient(client);
//        if(c == null){
//            return;
//        }
//        Map map = new HashMap();
//        map.put("mac",c.getId());
//        StbEntity stbEntity = stbService.findByMAC(map);
//        if (stbEntity == null){
//            return;
//        }
//        ThemeEntity themeEntity = themeService.findById(tid);
////        List<ThemeEntity> themeEntities = themeService.findByPublishAndDeviceNormal(pid,stbEntity.getId());
//        scountThemeService.saveCountTheme(themeEntity,stbEntity.getId());
//    }
    //终端设置 （音量，重启，终端版本更新，分辨率，内存信息）
    @OnEvent("setStb")
    public void setStb(SocketIOClient client, AckRequest request,String json){
        Gson gson = new Gson();
        StbLog log = gson.fromJson(json, StbLog.class);
        Client c = clientManage.getClient(client);
        if(c == null){
          return;
        }
        Map map = new HashMap();
        map.put("mac",c.getId());
        StbEntity stbEntity = stbService.findByMAC(map);
        if (stbEntity == null){
            return;
        }
        SlogEntity slogEntity = new SlogEntity(stbEntity.getId(),new Date());
        switch (log.getId()){
            case StbLog.STATU_OPEN:
                slogEntity.setContentType(StbLog.STATU_OPEN);
                slogEntity.setContent(StbLog.MSG_OPEN);
                slogService.insert(slogEntity);
                break;
            case StbLog.STATU_CLOSE:
                slogEntity.setContentType(StbLog.STATU_CLOSE);
                slogEntity.setContent(StbLog.MSG_CLOSE);
                slogService.insert(slogEntity);
                break;
            case StbLog.STATU_REBOOT:
                slogEntity.setContentType(StbLog.STATU_REBOOT);
                slogEntity.setContent(StbLog.MSG_REBOOT);
                slogService.insert(slogEntity);
                break;
            case StbLog.STATU_RESOLUTIOAN:
                slogEntity.setContentType(StbLog.STATU_RESOLUTIOAN);
                slogEntity.setContent(StbLog.MSG_RESOLUTIOAN+log.getSetvalue());
                slogService.insert(slogEntity);
                //修改终端信息
//                stbEntity.setWidth(log.getSetvalue());
                stbService.updateById(stbEntity);
                break;
            case StbLog.STATU_VOICE:
                slogEntity.setContentType(StbLog.STATU_VOICE);
                slogEntity.setContent(StbLog.MSG_VOICE+log.getSetvalue());
                slogService.insert(slogEntity);
                //修改终端信息
                stbEntity.setVoiceSet(Integer.valueOf(log.getSetvalue()));
                stbService.updateById(stbEntity);
                break;
            case StbLog.STATU_VERSION:
                slogEntity.setContentType(StbLog.STATU_VERSION);
                slogEntity.setContent(StbLog.MSG_VERSION+log.getSetvalue());
                slogService.insert(slogEntity);
                //修改终端信息
                stbEntity.setVersion(log.getSetvalue());
                stbService.updateById(stbEntity);
                break;
            case StbLog.STATU_RAM:
                slogEntity.setContentType(StbLog.STATU_RAM);
                slogEntity.setContent(StbLog.MSG_RAM+log.getSetvalue());
                slogService.insert(slogEntity);
                break;
            case StbLog.STATU_PROGRAM:
                ThemeEntity themeEntity = themeService.findById(Long.valueOf(log.getSetvalue()));
                if(themeEntity != null){
                    slogEntity.setContentType(StbLog.STATU_PROGRAM);
                    slogEntity.setContent(StbLog.MSG_PROGRAM+themeEntity.getTitle());
                    slogService.insert(slogEntity);
                    //更新终端正在播放节目
                    stbEntity.setProgramId(themeEntity.getId());
                    stbService.updateById(stbEntity);
                    //终端节目素材统计
                    scountThemeService.saveCountTheme(themeEntity,stbEntity.getId());
                }

                break;
            case StbLog.STATU_SKIN:
                slogEntity.setContentType(StbLog.STATU_SKIN);
                slogEntity.setContent(StbLog.MSG_SKIN+log.getSetvalue());
                slogService.insert(slogEntity);
                break;
        }

    }


    private void addClinet(SocketIOClient client,String mac){
        String ip = client.getHandshakeData().getAddress().toString();
        logger.info("终端成功上线：   ip [" + ip.substring(1) + "]" + "设备号："+mac);
        clientManage.delClientByIp(ip.substring(1));
        Client node = new Client();
        node.setIp(ip.substring(1));
        node.setClient(client);
        node.setId(mac);
        clientManage.addClient(node);

    }

   private void updateStbEntity(StbEntity stbEntity,StbSet stbSet){
       if(stbSet.getRam() != null && !stbSet.getRam().trim().isEmpty()){
           //记录终端日志
           stbEntity.setRam(stbSet.getRam());
       }
       if(stbSet.getReboot() != null && stbSet.getReboot() == 1){

           //记录终端重启

       }
       if(stbSet.getVoice() != null ){
           //声音改变日志
           stbEntity.setVoiceSet(stbSet.getVoice());
       }

       if (stbSet.getWidth() != null){
           //分辨率调整日志

           stbEntity.setWidth(stbSet.getWidth());
           stbEntity.setHeight(stbSet.getHeight());
       }
       stbService.updateById(stbEntity);
   }

    @OnEvent(value = "screen-shot")
    public void screenShot(SocketIOClient client, AckRequest request, String id, String img) throws IOException {
        Client c = clientManage.getClient(client);
        if (c != null) {
            Map map = new HashMap();
            map.put("mac",c.getId());
            StbEntity stb = stbService.findByMAC(map);
            String filePath = "/shot/" + stb.getId() + new Date().getTime() + ".png";
            File file = new File(configService.getValue("root_path") + filePath);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(Base64.getDecoder().decode(img));
            stb.setScreenPic(filePath);
            stbService.updateById(stb);
        }

        Client web = clientManage.getWebById(id);
        if (web != null){
            web.getClient().sendEvent("screen-shot-done",img);
        }

    }
}
