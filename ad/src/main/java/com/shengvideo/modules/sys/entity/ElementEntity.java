package com.shengvideo.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:35:02
 */
@TableName("element")
public class ElementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long pid;
	/**
	 * 元素类型
	 */
	private String type;
	/**
	 * 图片地址
	 */
	private String imgSrc;
	/**
	 * 视频地址
	 */
	private String videoSrc;
	/**
	 * x轴
	 */
	private Integer left;
	/**
	 * y轴
	 */
	private Integer top;
	/**
	 * 宽
	 */
	private Integer width;
	/**
	 * 高
	 */
	private Integer height;
	/**
	 * 行高
	 */
	private Integer lineHeight;
	/**
	 * 动画名称
	 */
	private String animatedName;
	/**
	 * 持续时间
	 */
	private Integer duration;
	/**
	 * 
	 */
	private Integer delay;
	/**
	 * 
	 */
	private Boolean playing;
	/**
	 * 
	 */
	private Boolean loop;
	/**
	 * 透明度
	 */
	private Integer opacity;
	/**
	 * 旋转角度
	 */
	private Integer transform;
	/**
	 * 文本
	 */
	private String text;
	/**
	 * 文本位置
	 */
	private String textAlign;
	/**
	 * 图标编号
	 */
	private String iconKey;
	/**
	 * 背景
	 */
	private String bg;
	/**
	 * 字体大小
	 */
	private Integer fontSize;
	/**
	 * 字体
	 */
	private String fontFamily;
	/**
	 * 字体粗度
	 */
	private String fontWeight;
	/**
	 * 字体颜色
	 */
	private String color;
	/**
	 * 
	 */
	private Integer zindex;
	/**
	 * 元素ID
	 */
	private Long mid;


	private String extend;

	private Integer stretch;

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
	 * 设置：
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * 设置：元素类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：元素类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImgSrc() {
		return imgSrc;
	}
	/**
	 * 设置：视频地址
	 */
	public void setVideoSrc(String videoSrc) {
		this.videoSrc = videoSrc;
	}
	/**
	 * 获取：视频地址
	 */
	public String getVideoSrc() {
		return videoSrc;
	}
	/**
	 * 设置：x轴
	 */
	public void setLeft(Integer left) {
		this.left = left;
	}
	/**
	 * 获取：x轴
	 */
	public Integer getLeft() {
		return left;
	}
	/**
	 * 设置：y轴
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：y轴
	 */
	public Integer getTop() {
		return top;
	}
	/**
	 * 设置：宽
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * 获取：宽
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * 设置：高
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	/**
	 * 获取：高
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * 设置：行高
	 */
	public void setLineHeight(Integer lineHeight) {
		this.lineHeight = lineHeight;
	}
	/**
	 * 获取：行高
	 */
	public Integer getLineHeight() {
		return lineHeight;
	}
	/**
	 * 设置：动画名称
	 */
	public void setAnimatedName(String animatedName) {
		this.animatedName = animatedName;
	}
	/**
	 * 获取：动画名称
	 */
	public String getAnimatedName() {
		return animatedName;
	}
	/**
	 * 设置：持续时间
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * 获取：持续时间
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * 设置：
	 */
	public void setDelay(Integer delay) {
		this.delay = delay;
	}
	/**
	 * 获取：
	 */
	public Integer getDelay() {
		return delay;
	}
	/**
	 * 设置：
	 */
	public void setPlaying(Boolean playing) {
		this.playing = playing;
	}
	/**
	 * 获取：
	 */
	public Boolean getPlaying() {
		return playing;
	}
	/**
	 * 设置：
	 */
	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	/**
	 * 获取：
	 */
	public Boolean getLoop() {
		return loop;
	}
	/**
	 * 设置：透明度
	 */
	public void setOpacity(Integer opacity) {
		this.opacity = opacity;
	}
	/**
	 * 获取：透明度
	 */
	public Integer getOpacity() {
		return opacity;
	}
	/**
	 * 设置：旋转角度
	 */
	public void setTransform(Integer transform) {
		this.transform = transform;
	}
	/**
	 * 获取：旋转角度
	 */
	public Integer getTransform() {
		return transform;
	}
	/**
	 * 设置：文本
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 获取：文本
	 */
	public String getText() {
		return text;
	}
	/**
	 * 设置：文本位置
	 */
	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}
	/**
	 * 获取：文本位置
	 */
	public String getTextAlign() {
		return textAlign;
	}
	/**
	 * 设置：图标编号
	 */
	public void setIconKey(String iconKey) {
		this.iconKey = iconKey;
	}
	/**
	 * 获取：图标编号
	 */
	public String getIconKey() {
		return iconKey;
	}
	/**
	 * 设置：背景
	 */
	public void setBg(String bg) {
		this.bg = bg;
	}
	/**
	 * 获取：背景
	 */
	public String getBg() {
		return bg;
	}
	/**
	 * 设置：字体大小
	 */
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	/**
	 * 获取：字体大小
	 */
	public Integer getFontSize() {
		return fontSize;
	}
	/**
	 * 设置：字体
	 */
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	/**
	 * 获取：字体
	 */
	public String getFontFamily() {
		return fontFamily;
	}
	/**
	 * 设置：字体粗度
	 */
	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}
	/**
	 * 获取：字体粗度
	 */
	public String getFontWeight() {
		return fontWeight;
	}
	/**
	 * 设置：字体颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：字体颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：
	 */
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	/**
	 * 获取：
	 */
	public Integer getZindex() {
		return zindex;
	}
	/**
	 * 设置：元素ID
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}
	/**
	 * 获取：元素ID
	 */
	public Long getMid() {
		return mid;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public Integer getStretch() {
		return stretch;
	}

	public void setStretch(Integer stretch) {
		this.stretch = stretch;
	}
}
