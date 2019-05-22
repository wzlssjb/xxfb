package com.shengvideo.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shengvideo.modules.sys.entity.ThemeClassEntity;
import com.shengvideo.modules.sys.service.ThemeClassService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-12-22 13:25:15
 */
@RestController
@RequestMapping("sys/themeclass")
public class ThemeClassController extends AbstractController {
    @Autowired
    private ThemeClassService themeClassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:theme:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = themeClassService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/all")
    @RequiresPermissions("sys:theme:list")
    public R all(@RequestParam Map<String, Object> params){
        return R.ok().put("data", themeClassService.selectByMap(params));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:theme:info")
    public R info(@PathVariable("id") Integer id){
			ThemeClassEntity themeClass = themeClassService.selectById(id);

        return R.ok().put("themeClass", themeClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:theme:save")
    public R save(@RequestBody ThemeClassEntity themeClass){
        themeClass.setCreateTime(new Date());
        themeClass.setCreateUser(getUserId());
        themeClassService.insert(themeClass);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:theme:update")
    public R update(@RequestBody ThemeClassEntity themeClass){
			themeClassService.updateById(themeClass);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:theme:delete")
    public R delete(@RequestBody Integer[] ids){
			themeClassService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
