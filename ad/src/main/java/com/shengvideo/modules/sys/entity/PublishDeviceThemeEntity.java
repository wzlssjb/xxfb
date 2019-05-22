package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-12 13:22:37
 */
@TableName("publish_device_theme")
public class PublishDeviceThemeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 发布
	 */
	private Long pid;
	/**
	 * 设备
	 */
	private Long did;
	/**
	 * 节目
	 */
	private Long tid;
	/**
	 * 有效状态 0.无效 1.正常
	 */
	private Integer status;
	/**
	 * 同步进度;0.未下载1.下载种2.下载完成
	 */
	private Integer synchronize;

	@TableField(exist = false)
	private String dName;

	@TableField(exist = false)
	private String tName;

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
	 * 设置：发布
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：发布
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * 设置：设备
	 */
	public void setDid(Long did) {
		this.did = did;
	}
	/**
	 * 获取：设备
	 */
	public Long getDid() {
		return did;
	}
	/**
	 * 设置：节目
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}
	/**
	 * 获取：节目
	 */
	public Long getTid() {
		return tid;
	}
	/**
	 * 设置：有效状态 0.无效 1.正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：有效状态 0.无效 1.正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：同步进度;0.未下载1.下载种2.下载完成
	 */
	public void setSynchronize(Integer synchronize) {
		this.synchronize = synchronize;
	}
	/**
	 * 获取：同步进度;0.未下载1.下载种2.下载完成
	 */
	public Integer getSynchronize() {
		return synchronize;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
}
