package com.shengvideo.modules.sys.form;

import com.shengvideo.modules.sys.entity.RollMsgEntity;

public class AddMsgForm {
    private Long[] dids;
    private RollMsgEntity msg;

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }

    public RollMsgEntity getMsg() {
        return msg;
    }

    public void setMsg(RollMsgEntity msg) {
        this.msg = msg;
    }
}
