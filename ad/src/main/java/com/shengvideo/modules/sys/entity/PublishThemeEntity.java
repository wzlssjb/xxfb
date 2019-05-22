package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 发布节目;
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@TableName("publish_theme")
public class PublishThemeEntity implements Serializable {
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
	 * 节目id
	 */
	private Long tid;

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
	 * 设置：节目id
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}
	/**
	 * 获取：节目id
	 */
	public Long getTid() {
		return tid;
	}
}
