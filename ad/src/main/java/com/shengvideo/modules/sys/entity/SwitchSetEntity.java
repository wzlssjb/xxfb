package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-20 18:02:35
 */
@TableName("switch_set")
public class SwitchSetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 周期,号隔开
	 */
	private String week;
	/**
	 * 开机时间
	 */
	private Date openTime;
	/**
	 * 关机时间
	 */
	private Date closeTime;
	/**
	 * 设备id
	 */
	private Long did;

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
	 * 设置：周期,号隔开
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 获取：周期,号隔开
	 */
	public String getWeek() {
		return week;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * 设置：设备id
	 */
	public void setDid(Long did) {
		this.did = did;
	}
	/**
	 * 获取：设备id
	 */
	public Long getDid() {
		return did;
	}
}
