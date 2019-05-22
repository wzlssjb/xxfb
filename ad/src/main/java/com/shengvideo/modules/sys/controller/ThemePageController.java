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

import com.shengvideo.modules.sys.entity.ThemePageEntity;
import com.shengvideo.modules.sys.service.ThemePageService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@RestController
@RequestMapping("sys/themepage")
public class ThemePageController {
    @Autowired
    private ThemePageService themePageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:themepage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = themePageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:themepage:info")
    public R info(@PathVariable("id") Long id){
			ThemePageEntity themePage = themePageService.selectById(id);

        return R.ok().put("themePage", themePage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:themepage:save")
    public R save(@RequestBody ThemePageEntity themePage){
			themePageService.insert(themePage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:themepage:update")
    public R update(@RequestBody ThemePageEntity themePage){
			themePageService.updateById(themePage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:themepage:delete")
    public R delete(@RequestBody Long[] ids){
			themePageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
