package com.shengvideo.modules.sys.form;

public class MobilePushForm {
    private Long mid;
    private Long[] dids;
    private Integer stretch;
    private Integer duration;
    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }

    public Integer getStretch() {
        return stretch;
    }

    public void setStretch(Integer stretch) {
        this.stretch = stretch;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
