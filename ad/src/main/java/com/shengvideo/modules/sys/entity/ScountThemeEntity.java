package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-14 15:49:32
 */
@TableName("stb_count_theme")
public class ScountThemeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 节目id
	 */
	private Long themeId;
	/**
	 * 终端id
	 */
	private Long stbId;
	/**
	 * 播放时间
	 */
	private Date playTime;

	@TableField(exist = false)
	private String themeName;

	@TableField(exist = false)
	private String mac;

	@TableField(exist = false)
	private Date startTime;

	@TableField(exist = false)
	private Date endTime;

	@TableField(exist = false)
	private Integer groupBy;  //按周，月，日统计

	@TableField(exist = false)
	private String groupTime; //分组时间

	@TableField(exist = false)
	private int count;  //播放次数

	public ScountThemeEntity (){

	}

	public ScountThemeEntity(Long themeId,Long stbId,Date playTime){
      this.themeId = themeId;
      this.stbId = stbId;
      this.playTime = playTime;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：节目id
	 */
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	/**
	 * 获取：节目id
	 */
	public Long getThemeId() {
		return themeId;
	}
	/**
	 * 设置：终端id
	 */
	public void setStbId(Long stbId) {
		this.stbId = stbId;
	}
	/**
	 * 获取：终端id
	 */
	public Long getStbId() {
		return stbId;
	}
	/**
	 * 设置：播放时间
	 */
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	/**
	 * 获取：播放时间
	 */
	public Date getPlayTime() {
		return playTime;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

	public Integer getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(Integer groupBy) {
		this.groupBy = groupBy;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
