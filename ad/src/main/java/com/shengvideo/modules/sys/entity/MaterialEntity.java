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
 * @date 2018-09-10 11:09:56
 */
@TableName("material")
public class MaterialEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	public static final Integer VEDIO = 1;

	public static final Integer IMG = 2;

	public static final Integer TEXT = 3;

	public static final Integer MUSIC = 4;

	public static final Integer ANIM = 5;

	public static final Integer APP = 6;
	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 分类id
	 */
	private Integer cid;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 文件大小 字节
	 */
	private Long fileSize;
	/**
	 * 0.未审核 1.测试阶段，2.审核通过 3.审核失败
	 */
	private Integer checkStatus;

    /**
     * 素材类型
     */
	private Integer type;

	private Integer width;

	private Integer height;

	private String originalFile;
    /**
     * 用户名
     */
    @TableField(exist = false)
    private String uName;

    /**
     * 目录名
     */
    @TableField(exist = false)
    private String cName;



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
	 * 设置：分类id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：分类id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：文件名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：文件名
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：文件路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：包名
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * 获取：包名
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateUser() {
		return createUser;
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
	 * 设置：文件大小 字节
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 获取：文件大小 字节
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * 设置：0.未审核 1.测试阶段，2.审核通过 3.审核失败
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：0.未审核 1.测试阶段，2.审核通过 3.审核失败
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcName() {
        return cName;
    }

    public String getuName() {
        return uName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
}
