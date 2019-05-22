package com.shengvideo.modules.push.vo;

import com.corundumstudio.socketio.SocketIOClient;

public class Client {
    private String id;
    private String ip;
    private SocketIOClient client;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public SocketIOClient getClient() {
        return client;
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
