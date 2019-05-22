package com.shengvideo.modules.push.server.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.shengvideo.modules.push.service.ClientManage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServerExceptionListener implements ExceptionListener {
    private Logger log = LoggerFactory.getLogger(ServerExceptionListener.class);

    private ClientManage clientManage;

    public ServerExceptionListener(ClientManage clientManage) {
        this.clientManage = clientManage;
    }

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        e.printStackTrace();
        log.info("onEventException",args);
        log.info(client.getRemoteAddress().toString());
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        log.info("onDisconnectException");
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        log.info("onConnectException");
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        String ip = ctx.pipeline().channel().read().remoteAddress().toString();
        clientManage.delClientByIp(ip.substring(1,ip.length()));
        return true;
    }
}
