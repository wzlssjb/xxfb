package com.shengvideo.modules.sys.vo;

import java.util.List;

public class RemoteInstallVo {
    private List<String> macs;
    private List<Long>  mids;
    private int installType;
    public List<String> getMacs() {
        return macs;
    }

    public void setMacs(List<String> macs) {
        this.macs = macs;
    }

    public List<Long> getMids() {
        return mids;
    }

    public void setMids(List<Long> mids) {
        this.mids = mids;
    }

    public int getInstallType() {
        return installType;
    }

    public void setInstallType(int installType) {
        this.installType = installType;
    }
}
