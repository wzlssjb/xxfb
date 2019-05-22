package com.shengvideo.modules.sys.form;

import com.shengvideo.modules.sys.entity.ElementEntity;

public class TextPushForm {
    private ElementEntity element;
    private Long[] dids;

    public ElementEntity getElement() {
        return element;
    }

    public void setElement(ElementEntity element) {
        this.element = element;
    }

    public Long[] getDids() {
        return dids;
    }

    public void setDids(Long[] dids) {
        this.dids = dids;
    }
}
