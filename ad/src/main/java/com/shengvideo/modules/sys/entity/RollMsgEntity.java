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
 * @date 2018-11-22 11:00:55
 */
@TableName("roll_msg")
public class RollMsgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 速度
	 */
	private Integer speed;
    /**
     * 1.慢 2.一般 3.快 4.很快
     */
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 大小
	 */
	private Integer size;
	/**
	 * 1.顶部 2.底部
	 */
	private Integer position;
	/**
	 * 时长/秒
	 */
	private Integer duration;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建用户
	 */
	private Long createUser;

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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：速度
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	/**
	 * 获取：速度
	 */
	public Integer getSpeed() {
		return speed;
	}
	/**
	 * 设置：颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：大小
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * 获取：大小
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * 设置：1.慢 2.一般 3.快 4.很快
	 */
	/**
	 * 设置：1.顶部 2.底部
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 * 获取：1.顶部 2.底部
	 */
	public Integer getPosition() {
		return position;
	}
	/**
	 * 设置：时长/秒
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * 获取：时长/秒
	 */
	public Integer getDuration() {
		return duration;
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
	 * 设置：创建用户
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户
	 */
	public Long getCreateUser() {
		return createUser;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}
}
