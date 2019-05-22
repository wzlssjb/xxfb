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
@TableName("theme_page")
public class ThemePageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 场景
	 */
	@TableId
	private Long id;
	/**
	 * 节目ID
	 */
	private Long tid;
	/**
	 * 背景音乐
	 */
	private String music;

    private Integer duration;

    private Long mid;

    @TableField(exist = false)
	private List<ElementEntity> elements;

	/**
	 * 设置：场景
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：场景
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：节目ID
	 */
	public void setTid(Long tid) {
		this.tid = tid;
	}
	/**
	 * 获取：节目ID
	 */
	public Long getTid() {
		return tid;
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

	public List<ElementEntity> getElements() {
		return elements;
	}

	public void setElements(List<ElementEntity> elements) {
		this.elements = elements;
	}

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}
}
