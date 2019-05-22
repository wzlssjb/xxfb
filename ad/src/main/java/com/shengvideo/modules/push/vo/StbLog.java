package com.shengvideo.modules.push.vo;

public class StbLog {
    private Integer id ;
    // 1开机  2关机  3重启 4改分辨率（有value值） 5改声音（有value值） 6改终端版本（有value值）
    // 7改内存（有value值） 8更新节目（有value值） 9更新模板（有value值）
    private String setvalue; //对应的值

    public final static int STATU_OPEN = 1;
    public final static int STATU_CLOSE = 2;
    public final static int STATU_REBOOT = 3;
    public final static int STATU_RESOLUTIOAN = 4;
    public final static int STATU_VOICE = 5;
    public final static int STATU_VERSION =6;
    public final static int STATU_RAM = 7;
    public final static int STATU_PROGRAM = 8;
    public final static int STATU_SKIN = 9;
    public final static int STATU_ONLINE = 10;
    public final static int STATU_OFFILINE = 11;

    public final static String MSG_OPEN="开机";
    public final static String MSG_CLOSE="关机";
    public final static String MSG_REBOOT="重启";
    public final static String MSG_RESOLUTIOAN="分辨率修改：";
    public final static String MSG_VOICE="声音修改";
    public final static String MSG_VERSION="修改版本号:";
    public final static String MSG_RAM="修改内存：";
    public final static String MSG_PROGRAM="修改节目：";
    public final static String MSG_SKIN="修改主题：";
    public final static String MSG_ONLINE="上线";
    public final static String MSG_OFFILINE="下线";


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetvalue() {
        return setvalue;
    }

    public void setSetvalue(String setvalue) {
        this.setvalue = setvalue;
    }
}
