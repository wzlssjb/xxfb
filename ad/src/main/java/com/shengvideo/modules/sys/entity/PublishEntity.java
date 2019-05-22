package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * publish;发布主表
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@TableName("publish")
public class PublishEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	@TableId
	private Long id;

	private Integer type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long uid;
	/**
	 * 发布状态;1.正常，2.下线
	 */
	private Integer status;
	/**
	 * 播放模式;1.轮播2.单次
	 */
	private Integer playMode;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 审核状态;0.未审核1.审核成功2.审核失败
	 */
	private Integer checkStatus;
	/**
	 * 审核人
	 */
	private String checker;
	/**
	 * 审核备注
	 */
	private String checkRemark;
	/**
	 * 是否插播;0.正常播放1.插播
	 */
	private Boolean interCut;
	/**
	 * 周循环
	 */
	private String cycleWeek;

    private Integer cycleMode;


    @TableField(exist = false)
    private List<PublishPlayTimeEntity> playTimes;

    @TableField(exist = false)
    private List<StbEntity> devices;

    @TableField(exist = false)
    private List<ThemeEntity> themes;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * 获取：创建人
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * 设置：发布状态;1.正常，2.下线
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：发布状态;1.正常，2.下线
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：播放模式;1.轮播2.单次
	 */
	public void setPlayMode(Integer playMode) {
		this.playMode = playMode;
	}
	/**
	 * 获取：播放模式;1.轮播2.单次
	 */
	public Integer getPlayMode() {
		return playMode;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：审核状态;0.未审核1.审核成功2.审核失败
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：审核状态;0.未审核1.审核成功2.审核失败
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}
	/**
	 * 设置：审核人
	 */
	public void setChecker(String checker) {
		this.checker = checker;
	}
	/**
	 * 获取：审核人
	 */
	public String getChecker() {
		return checker;
	}
	/**
	 * 设置：审核备注
	 */
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	/**
	 * 获取：审核备注
	 */
	public String getCheckRemark() {
		return checkRemark;
	}
	/**
	 * 设置：是否插播;0.正常播放1.插播
	 */
	public void setInterCut(Boolean interCut) {
		this.interCut = interCut;
	}
	/**
	 * 获取：是否插播;0.正常播放1.插播
	 */
	public Boolean getInterCut() {
		return interCut;
	}
	/**
	 * 设置：周循环
	 */
	public void setCycleWeek(String cycleWeek) {
		this.cycleWeek = cycleWeek;
	}
	/**
	 * 获取：周循环
	 */
	public String getCycleWeek() {
		return cycleWeek;
	}

	public List<PublishPlayTimeEntity> getPlayTimes(){
	    return playTimes;
    }
    public void setPlayTimes(List<PublishPlayTimeEntity> playTimes) {
        this.playTimes = playTimes;
    }

    public List<StbEntity> getDevices() {
        return devices;
    }

    public void setDevices(List<StbEntity> devices) {
        this.devices = devices;
    }

    public List<ThemeEntity> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeEntity> themes) {
        this.themes = themes;
    }

    public Integer getCycleMode() {
        return cycleMode;
    }

    public void setCycleMode(Integer cycleMode) {
        this.cycleMode = cycleMode;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
