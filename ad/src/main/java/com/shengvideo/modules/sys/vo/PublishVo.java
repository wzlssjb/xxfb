package com.shengvideo.modules.sys.vo;

import com.shengvideo.modules.sys.entity.ThemeEntity;

import java.util.List;

public class PublishVo {
    private Long id;
    private Boolean interCut;
    private List<ThemeEntity> themes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ThemeEntity> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeEntity> themes) {
        this.themes = themes;
    }

    public Boolean getInterCut() {
        return interCut;
    }

    public void setInterCut(Boolean interCut) {
        this.interCut = interCut;
    }
}
