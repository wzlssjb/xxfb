package com.shengvideo.modules.push.vo;

public class StbSet {

    private String mac;

    private String version;  //版本

    private Integer voice;   //音量

    private Integer reboot;  //重启

    private Integer width; //分辨率

    private Integer height;

    private String ram;      //内存信息

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getVoice() {
        return voice;
    }

    public void setVoice(Integer voice) {
        this.voice = voice;
    }

    public Integer getReboot() {
        return reboot;
    }

    public void setReboot(Integer reboot) {
        this.reboot = reboot;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
