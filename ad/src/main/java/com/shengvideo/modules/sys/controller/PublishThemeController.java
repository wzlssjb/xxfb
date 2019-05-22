package com.shengvideo.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shengvideo.modules.sys.entity.PublishThemeEntity;
import com.shengvideo.modules.sys.service.PublishThemeService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 发布节目;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@RestController
@RequestMapping("sys/publishtheme")
public class PublishThemeController {
    @Autowired
    private PublishThemeService publishThemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:publishtheme:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishThemeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:publishtheme:info")
    public R info(@PathVariable("id") Long id){
			PublishThemeEntity publishTheme = publishThemeService.selectById(id);

        return R.ok().put("publishTheme", publishTheme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:publishtheme:save")
    public R save(@RequestBody PublishThemeEntity publishTheme){
			publishThemeService.insert(publishTheme);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:publishtheme:update")
    public R update(@RequestBody PublishThemeEntity publishTheme){
			publishThemeService.updateById(publishTheme);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:publishtheme:delete")
    public R delete(@RequestBody Long[] ids){
			publishThemeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
