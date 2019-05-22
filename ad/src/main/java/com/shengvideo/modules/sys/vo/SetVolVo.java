package com.shengvideo.modules.sys.vo;

import com.shengvideo.modules.sys.entity.VolSetEntity;

import java.util.List;

public class SetVolVo {
    private Integer defaultVol;

    private List<VolSetEntity> vols;

    public List<VolSetEntity> getVols() {
        return vols;
    }

    public void setVols(List<VolSetEntity> vols) {
        this.vols = vols;
    }

    public Integer getDefaultVol() {
        return defaultVol;
    }

    public void setDefaultVol(Integer defaultVol) {
        this.defaultVol = defaultVol;
    }
}
