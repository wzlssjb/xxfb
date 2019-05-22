package com.shengvideo.modules.sys.form;

import com.shengvideo.modules.sys.entity.VolSetEntity;

import java.util.List;

public class SetVolForm {
    private Long[] dids;
    private Integer defaultVol;
    private List<VolSetEntity> vols;

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }

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
