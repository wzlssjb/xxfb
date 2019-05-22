package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@TableName("theme")
public class ThemeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 节目名称
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 类型 1.普通
	 */
	private Integer type;
	/**
	 * 节目高度
	 */
	private Integer canvasHeight;
	/**
	 * 节目宽度
	 */
	private Integer canvasWidth;
	/**
	 * 背景音乐
	 */
	private String music;

	private Integer cid;

    @TableField(exist = false)
	private List<ThemePageEntity> pages;
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
	 * 设置：节目名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：节目名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：类型 1.普通
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型 1.普通
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：节目高度
	 */
	public void setCanvasHeight(Integer canvasHeight) {
		this.canvasHeight = canvasHeight;
	}
	/**
	 * 获取：节目高度
	 */
	public Integer getCanvasHeight() {
		return canvasHeight;
	}
	/**
	 * 设置：节目宽度
	 */
	public void setCanvasWidth(Integer canvasWidth) {
		this.canvasWidth = canvasWidth;
	}
	/**
	 * 获取：节目宽度
	 */
	public Integer getCanvasWidth() {
		return canvasWidth;
	}
	/**
	 * 设置：背景音乐
	 */
	public void setMusic(String music) {
		this.music = music;
	}
	/**
	 * 获取：背景音乐
	 */
	public String getMusic() {
		return music;
	}

	public List<ThemePageEntity> getPages() {
		return pages;
	}

	public void setPages(List<ThemePageEntity> pages) {
		this.pages = pages;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
