package com.shengvideo.modules.sys.vo;

import com.shengvideo.modules.sys.entity.SgroupEntity;

import java.util.List;

public class SkinPublishVo
{
    Integer skinId; //主题id
    List<Integer> groupId;   //终端分组id
    int siteMatch;         //模板匹配
    int solutionMatch;    //分辨率匹配

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public List<Integer> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Integer> groupId) {
        this.groupId = groupId;
    }

    public int getSiteMatch() {
        return siteMatch;
    }

    public void setSiteMatch(int siteMatch) {
        this.siteMatch = siteMatch;
    }

    public int getSolutionMatch() {
        return solutionMatch;
    }

    public void setSolutionMatch(int solutionMatch) {
        this.solutionMatch = solutionMatch;
    }
}
