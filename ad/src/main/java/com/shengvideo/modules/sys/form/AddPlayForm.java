package com.shengvideo.modules.sys.form;

import java.util.Date;

public class AddPlayForm {
    private Long[] mids;
    private Long[] dids;
    private Date startTime;
    private Date endTime;

    public Long[] getMids() {
        return mids;
    }

    public void setMids(Long[] mids) {
        this.mids = mids;
    }

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }

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
}
