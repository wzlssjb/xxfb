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

import com.shengvideo.modules.sys.entity.ThemeUseMtEntity;
import com.shengvideo.modules.sys.service.ThemeUseMtService;
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
@RequestMapping("sys/themeusemt")
public class ThemeUseMtController {
    @Autowired
    private ThemeUseMtService themeUseMtService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:themeusemt:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = themeUseMtService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:themeusemt:info")
    public R info(@PathVariable("id") Long id){
			ThemeUseMtEntity themeUseMt = themeUseMtService.selectById(id);

        return R.ok().put("themeUseMt", themeUseMt);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:themeusemt:save")
    public R save(@RequestBody ThemeUseMtEntity themeUseMt){
			themeUseMtService.insert(themeUseMt);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:themeusemt:update")
    public R update(@RequestBody ThemeUseMtEntity themeUseMt){
			themeUseMtService.updateById(themeUseMt);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:themeusemt:delete")
    public R delete(@RequestBody Long[] ids){
			themeUseMtService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
