package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ScountThemeEntity;
import com.shengvideo.modules.sys.entity.ThemeEntity;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-14 15:49:32
 */
public interface ScountThemeService extends IService<ScountThemeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageTheme(Map<String, Object> params);

    public void saveCountTheme(ThemeEntity themeEntity, long stbId);
}

