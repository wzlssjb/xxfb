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

import com.shengvideo.modules.sys.entity.ScountThemeEntity;
import com.shengvideo.modules.sys.service.ScountThemeService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-14 15:49:32
 */
@RestController
@RequestMapping("sys/scounttheme")
public class ScountThemeController {
    @Autowired
    private ScountThemeService scountThemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:scounttheme:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scountThemeService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/list/theme")
    @RequiresPermissions("sys:scounttheme:list")
    public R listTheme(@RequestParam Map<String, Object> params){
        PageUtils page = scountThemeService.queryPageTheme(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:scounttheme:info")
    public R info(@PathVariable("id") Long id){
			ScountThemeEntity scountTheme = scountThemeService.selectById(id);

        return R.ok().put("scountTheme", scountTheme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:scounttheme:save")
    public R save(@RequestBody ScountThemeEntity scountTheme){
			scountThemeService.insert(scountTheme);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:scounttheme:update")
    public R update(@RequestBody ScountThemeEntity scountTheme){
			scountThemeService.updateById(scountTheme);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:scounttheme:delete")
    public R delete(@RequestBody Long[] ids){
			scountThemeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
