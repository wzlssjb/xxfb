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

import com.shengvideo.modules.sys.entity.SwitchSetEntity;
import com.shengvideo.modules.sys.service.SwitchSetService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-11-20 18:02:35
 */
@RestController
@RequestMapping("sys/switchset")
public class SwitchSetController {
    @Autowired
    private SwitchSetService switchSetService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:switchset:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = switchSetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:switchset:info")
    public R info(@PathVariable("id") Long id){
			SwitchSetEntity switchSet = switchSetService.selectById(id);

        return R.ok().put("switchSet", switchSet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:switchset:save")
    public R save(@RequestBody SwitchSetEntity switchSet){
			switchSetService.insert(switchSet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:switchset:update")
    public R update(@RequestBody SwitchSetEntity switchSet){
			switchSetService.updateById(switchSet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:switchset:delete")
    public R delete(@RequestBody Long[] ids){
			switchSetService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
