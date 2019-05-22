package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 发布设备;
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@TableName("publish_device")
public class PublishDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 发布id
	 */
	private Long pid;
	/**
	 * 设备id
	 */
	private Long did;
	/**
	 * 发布状态;1.正常2.下线
	 */
	private Integer status;
	/**
	 * 同步进度;0.未下载1.下载种2.下载完成
	 */
	private Integer synchronize;

    @TableField(exist = false)
	private StbEntity device;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：发布id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：发布id
	 */
	public Long getPid() {
		return pid;
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
	/**
	 * 设置：发布状态;1.正常2.下线
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：发布状态;1.正常2.下线
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

    public StbEntity getDevice() {
        return device;
    }

    public void setDevice(StbEntity device) {
        this.device = device;
    }
}
