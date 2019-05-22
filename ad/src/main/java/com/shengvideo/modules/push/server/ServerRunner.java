package com.shengvideo.modules.push.server;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.shengvideo.modules.push.server.handler.NodeEventHandler;
import com.shengvideo.modules.push.server.handler.WebEventHandler;
import com.shengvideo.modules.sys.service.StbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements CommandLineRunner {
    private final SocketIOServer server;
    @Autowired
    private NodeEventHandler nodeEventHandler;

    @Autowired
    private WebEventHandler webEventHandler;

    @Autowired
    StbService stbService;

    private final SocketIONamespace nodeSocketNameSpace;


    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
        nodeSocketNameSpace = server.addNamespace("/phone");

    }

    @Bean(name = "nodeNamespace")
    public SocketIONamespace getNOdeSocketIONameSpace(SocketIOServer server) {
        nodeSocketNameSpace.addListeners(nodeEventHandler);
        return nodeSocketNameSpace;
    }

    public void run(String... args) throws Exception {
        stbService.initOnline();
        server.addListeners(webEventHandler);
        server.start();
    }
}