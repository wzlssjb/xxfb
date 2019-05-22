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
 * @date 2018-11-13 16:51:06
 */
@TableName("stb_log")
public class SlogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 终端id
	 */
	private Long stbId;

	@TableField(exist = false)
	private String mac;
	/**
	 * 类型1开机  2关机  3重启 4改分辨率 5改声音 6改终端版本7改内存 8更新节目 9更新模板
	 */
	private Integer contentType;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 时间
	 */
	private Date operateTime;

	@TableField(exist = false)
	private Date startTime;

	@TableField(exist = false)
	private Date endTime;

	public SlogEntity(){

	}
	public SlogEntity(long stbId,Date operateTime){
         this.stbId = stbId;
         this.operateTime = operateTime;

	}
	public SlogEntity(long stbId,Integer contentType,String content, Date operateTime){
		this.stbId = stbId;
		this.contentType = contentType;
		this.content = content;
		this.operateTime = operateTime;

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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 设置：类型1开机  2关机  3重启 4改分辨率 5改声音 6改终端版本7改内存 8更新节目 9更新模板
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	/**
	 * 获取：类型1开机  2关机  3重启 4改分辨率 5改声音 6改终端版本7改内存 8更新节目 9更新模板
	 */
	public Integer getContentType() {
		return contentType;
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
	 * 设置：时间
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * 获取：时间
	 */
	public Date getOperateTime() {
		return operateTime;
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
}
