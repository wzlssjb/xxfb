package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
@TableName("material_class")
public class MaterialClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父id
	 */
	private Integer pid;
	/**
	 * 创建时间
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private Long creatUser;

    @TableField(exist = false)
	private List<MaterialClassEntity> children;
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：父id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父id
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatTime() {
		return creatTime;
	}
	/**
	 * 设置：
	 */
	public void setCreatUser(Long creatUser) {
		this.creatUser = creatUser;
	}
	/**
	 * 获取：
	 */
	public Long getCreatUser() {
		return creatUser;
	}

	public List<MaterialClassEntity> getChildren() {
		return children;
	}

	public void setChildren(List<MaterialClassEntity> children) {
		this.children = children;
	}
}
