package com.shengvideo.modules.push.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.shengvideo.modules.push.vo.Client;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.service.StbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientManage {

    @Autowired
    private StbService stbService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<Client> clients;

    private List<Client> webs;

    public ClientManage() {
        this.clients = new ArrayList<>();
        this.webs = new ArrayList<>();
    }

    public void addClient(Client client){
        delNode(client);
        clients.add(client);
    }


    public Client getClientByIp(String ip){
        for (Client c:
                clients) {
            if (c.getIp().equals(ip)){
                return c;
            }
        }
        return null;
    }

    public Client getByMac(String id){
        for (Client c:
                clients) {
            if (c.getId() != null && !c.getId().trim().isEmpty() && c.getId().toLowerCase().equals(id.toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public Client getNode(Client client){
        for (Client c:
                clients) {
            if ( c.getId() != null && c.getId().equals(client.getId())){
                return c;
            }
        }
        return null;
    }


    public Client getClient(SocketIOClient client){
        for (Client c:
                clients) {
            if (c.getClient().equals(client)){
                return c;
            }
        }
        return null;
    }

    public void delNode(Client client){
        Client node = getNode(client);
        if (node != null){
            node.getClient().disconnect();
            remove(node);
        }
    }


    public void delClientByIp(String ip){
        Client c = getClientByIp(ip);
        if (c != null){
            remove(c);
        }
    }

    public void delClient(SocketIOClient client){
        Client c = getClient(client);
        if (c != null){
            c.getClient().disconnect();
            logger.info("客户端:" + c.getId() + " 断开连接");
            remove(c);
        }
    }

    //把终端设置为下线
    private void remove(Client c){
        Map map = new HashMap();
        map.put("mac",c.getId());
        StbEntity d = stbService.findByMAC(map);
        if (d != null){
            d.setOnlineStatus(StbEntity.ONLINE_FALSE);
            stbService.updateById(d);
        }
        clients.remove(c);
    }

    public void addWeb(Client web){
        this.webs.add(web);
    }

    public Client getWebByClient (SocketIOClient c){
        for(Client client:webs){
            if (client.getClient().equals(c)){
                return client;
            }
        }
        return null;
    }

    public Client getWebById (String id){
        for(Client client:webs){
            if (client.getId().equals(id)){
                return client;
            }
        }
        return null;
    }

    public void delWeb(Client web){
        this.webs.remove(web);
    }
}
