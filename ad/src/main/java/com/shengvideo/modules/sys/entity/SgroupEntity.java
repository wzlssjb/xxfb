package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@TableName("stb_group")
public class SgroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分组名称
	 */
	private String name;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 公交线路id
	 */
	private Integer routeId;
	/**
	 * 1 是 0否
	 */
	private Boolean defaltGroup;


	@TableField(exist = false)
	private List<SgroupEntity> children;


	@TableField(exist = false)
	private Integer stbCount;

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
	 * 设置：分组名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分组名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父ID
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 获取公交线路Id
	 * @return
	 */
	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * 设置：1 是 0否
	 */
	public void setDefaltGroup(Boolean defaltGroup) {
		this.defaltGroup = defaltGroup;
	}
	/**
	 * 获取：1 是 0否
	 */
	public Boolean getDefaltGroup() {
		return defaltGroup;
	}

	public List<SgroupEntity> getChildren() {
		return children;
	}

	public void setChildren(List<SgroupEntity> children) {
		this.children = children;
	}


	public void setStbCount(Integer stbCount) {
		this.stbCount = stbCount;
	}

	public Integer getStbCount() {
		return stbCount;
	}
}
