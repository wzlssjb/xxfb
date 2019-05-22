package com.shengvideo.config;

import org.springframework.context.annotation.Configuration;

@Configuration("authoConfig")
public class AuthoConfig {
    private boolean lose;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }
}
