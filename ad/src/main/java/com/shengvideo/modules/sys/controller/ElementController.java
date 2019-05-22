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

import com.shengvideo.modules.sys.entity.ElementEntity;
import com.shengvideo.modules.sys.service.ElementService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:35:02
 */
@RestController
@RequestMapping("sys/element")
public class ElementController {
    @Autowired
    private ElementService elementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:element:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = elementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:element:info")
    public R info(@PathVariable("id") Long id){
			ElementEntity element = elementService.selectById(id);

        return R.ok().put("element", element);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:element:save")
    public R save(@RequestBody ElementEntity element){
			elementService.insert(element);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:element:update")
    public R update(@RequestBody ElementEntity element){
			elementService.updateById(element);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:element:delete")
    public R delete(@RequestBody Long[] ids){
			elementService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
