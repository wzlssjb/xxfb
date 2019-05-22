package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.shengvideo.common.interfaces.ImportModel;
import com.shengvideo.common.interfaces.ModelProp;
import com.shengvideo.common.interfaces.ModelTitle;

import java.io.Serializable;

/**
 * 
 * 
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@TableName("stb")
@ModelTitle(name = "终端导入")
public class StbEntity extends ImportModel implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 分组id
	 */
	private Long stbGroupId;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 设备号
	 */
	@ModelProp(name = "MAC",colIndex = 0)
	private String mac;
	/**
	 * 节目id
	 */
	private Long programId;
	/**
	 * ip地址
	 */
	private String ipaddress;
	/**
	 * 屏幕截屏图片
	 */
	private String screenPic;
	/**
	 * 声音
	 */
	private Integer voiceSet;
	/**
	 * 1开 0关
	 */
	private Integer switchStatus;

	/**
	 * 状态上线下线
	 */
	private Integer onlineStatus;
	/**
	 * 分辨率
	 */
	/**
	 * 版本号
	 */
	private String version;
	@ModelProp(name = "分辨率/高（非必填）",colIndex = 4)
	private Integer physicsHeight;
	@ModelProp(name = "物理/宽（非必填）",colIndex = 3)
	private Integer physicsWidth;
	@ModelProp(name = "分辨率/宽（非必填）",colIndex = 1)
	private Integer width;
	@ModelProp(name = "分辨率/高（非必填）",colIndex = 2)
	private Integer height;
	/**
	 * 内存情况
	 */
	private String ram;

	@TableField(exist = false)
	private String groupName;

	@TableField(exist = false)
	private String programName;


	public static final int ONLINE_TRUE = 1;
	public static final int ONLINE_FALSE = 0;
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
	 * 设置：分组id
	 */
	public void setStbGroupId(Long stbGroupId) {
		this.stbGroupId = stbGroupId;
	}
	/**
	 * 获取：分组id
	 */
	public Long getStbGroupId() {
		return stbGroupId;
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
	 * 设置：设备号
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * 获取：设备号
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * 设置：节目id
	 */
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	/**
	 * 获取：节目id
	 */
	public Long getProgramId() {
		return programId;
	}
	/**
	 * 设置：ip地址
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	/**
	 * 获取：ip地址
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	/**
	 * 设置：屏幕截屏图片
	 */
	public void setScreenPic(String screenPic) {
		this.screenPic = screenPic;
	}
	/**
	 * 获取：屏幕截屏图片
	 */
	public String getScreenPic() {
		return screenPic;
	}
	/**
	 * 设置：声音
	 */
	public void setVoiceSet(Integer voiceSet) {
		this.voiceSet = voiceSet;
	}
	/**
	 * 获取：声音
	 */
	public Integer getVoiceSet() {
		return voiceSet;
	}
	/**
	 * 设置：1开 0关
	 */
	public void setSwitchStatus(Integer switchStatus) {
		this.switchStatus = switchStatus;
	}
	/**
	 * 获取：1开 0关
	 */
	public Integer getSwitchStatus() {
		return switchStatus;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：内存情况
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}
	/**
	 * 获取：内存情况
	 */
	public String getRam() {
		return ram;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}


	public Integer getPhysicsWidth() {
		return physicsWidth;
	}

	public void setPhysicsWidth(Integer physicsWidth) {
		this.physicsWidth = physicsWidth;
	}

	public Integer getPhysicsHeight() {
		return physicsHeight;
	}

	public void setPhysicsHeight(Integer physicsHeight) {
		this.physicsHeight = physicsHeight;
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
}
