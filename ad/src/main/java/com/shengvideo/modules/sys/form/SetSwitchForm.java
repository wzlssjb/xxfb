package com.shengvideo.modules.sys.form;

import com.shengvideo.modules.sys.entity.SwitchSetEntity;

import java.util.List;

/**
 * 定时开关机接收数据格式
 */
public class SetSwitchForm {

    private Long[] dids;
    private List<SwitchSetEntity> switchSetEntities;

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }

    public List<SwitchSetEntity> getSwitchSetEntities() {
        return switchSetEntities;
    }

    public void setSwitchSetEntities(List<SwitchSetEntity> switchSetEntities) {
        this.switchSetEntities = switchSetEntities;
    }

}
