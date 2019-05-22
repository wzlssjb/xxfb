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

import com.shengvideo.modules.sys.entity.SlogEntity;
import com.shengvideo.modules.sys.service.SlogService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-13 16:51:06
 */
@RestController
@RequestMapping("sys/slog")
public class SlogController {
    @Autowired
    private SlogService slogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:slog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = slogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:slog:info")
    public R info(@PathVariable("id") Long id){
			SlogEntity slog = slogService.selectById(id);

        return R.ok().put("slog", slog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:slog:save")
    public R save(@RequestBody SlogEntity slog){
			slogService.insert(slog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:slog:update")
    public R update(@RequestBody SlogEntity slog){
			slogService.updateById(slog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:slog:delete")
    public R delete(@RequestBody Long[] ids){
			slogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
