package com.shengvideo.modules.push.server.handler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.shengvideo.config.AuthoConfig;
import com.shengvideo.modules.push.service.ClientManage;
import com.shengvideo.modules.push.service.NodeService;
import com.shengvideo.modules.push.vo.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class WebEventHandler {
    private Logger logger = LoggerFactory.getLogger(WebEventHandler.class);
    @Autowired
    protected SocketIOServer server;

    @Autowired
    private ClientManage clientManage;

    @Autowired
    NodeService nodeService;

    @Autowired
    AuthoConfig authoConfig;
    @OnConnect
    public void  onConnect(SocketIOClient client){
        String ip = client.getHandshakeData().getAddress().toString();
        logger.info("终端连接   ip [" + ip.substring(1) + "]");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        clientManage.delWeb(clientManage.getWebByClient(client));
    }

    @Transactional
    @OnEvent(value = "register")
    public void register(SocketIOClient client, AckRequest request, String id) {
        logger.info("Admin 管理端 id:" + id);
        Client web = new Client();
        web.setClient(client);
        web.setId(id);
        clientManage.addWeb(web);
    }

    @OnEvent(value = "screen-shot")
    public void screenShot(SocketIOClient client, AckRequest request, String mac) {
        Client web = clientManage.getWebByClient(client);
        if (web != null){
            logger.info(web.getId() + "截屏操作 :" + mac);
            nodeService.send(mac,"screen-shot",web.getId());
        }

    }

}
