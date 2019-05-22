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

import com.shengvideo.modules.sys.entity.ScountMaterialEntity;
import com.shengvideo.modules.sys.service.ScountMaterialService;
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
@RequestMapping("sys/scountmaterial")
public class ScountMaterialController {
    @Autowired
    private ScountMaterialService scountMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:scountmaterial:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scountMaterialService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/list/material")
    @RequiresPermissions("sys:scountmaterial:list")
    public R listMaterial(@RequestParam Map<String, Object> params){
        PageUtils page = scountMaterialService.queryPageMaterial(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:scountmaterial:info")
    public R info(@PathVariable("id") Long id){
			ScountMaterialEntity scountMaterial = scountMaterialService.selectById(id);

        return R.ok().put("scountMaterial", scountMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:scountmaterial:save")
    public R save(@RequestBody ScountMaterialEntity scountMaterial){
			scountMaterialService.insert(scountMaterial);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:scountmaterial:update")
    public R update(@RequestBody ScountMaterialEntity scountMaterial){
			scountMaterialService.updateById(scountMaterial);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:scountmaterial:delete")
    public R delete(@RequestBody Long[] ids){
			scountMaterialService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
