package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@TableName("theme_use_mt")
public class ThemeUseMtEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 节目id
	 */
	private Long tid;
	/**
	 * 元素id
	 */
	private Long mid;

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
	public void setTid(Long tid) {
		this.tid = tid;
	}
	/**
	 * 获取：节目id
	 */
	public Long getTid() {
		return tid;
	}
	/**
	 * 设置：元素id
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}
	/**
	 * 获取：元素id
	 */
	public Long getMid() {
		return mid;
	}
}
