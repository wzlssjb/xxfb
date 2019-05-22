package com.shengvideo.modules.sys.vo;

import java.util.Date;
import java.util.List;

public class TimeRangOnPlayTheme {
    private Date startTime;
    private Date endTime;
    private List<PublishVo> publishs;
    private boolean on;
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<PublishVo> getPublishs() {
        return publishs;
    }

    public void setPublishs(List<PublishVo> publishs) {
        this.publishs = publishs;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
