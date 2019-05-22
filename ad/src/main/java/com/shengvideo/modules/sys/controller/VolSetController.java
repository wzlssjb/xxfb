package com.shengvideo.modules.sys.controller;

import com.shengvideo.common.utils.R;
import com.shengvideo.modules.sys.entity.VolSetEntity;
import com.shengvideo.modules.sys.service.VolSetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-19 16:26:50
 */
@RestController
@RequestMapping("sys/volset")
public class VolSetController {
    @Autowired
    private VolSetService volSetService;

    /**
     * 列表
     */

    @RequestMapping("/list")
    public R list(Long did){
        return R.ok().put("list", volSetService.selectByDevice(did));
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:volset:info")
    public R info(@PathVariable("id") Long id){
			VolSetEntity volSet = volSetService.selectById(id);

        return R.ok().put("volSet", volSet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:volset:save")
    public R save(@RequestBody VolSetEntity volSet){
			volSetService.insert(volSet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:volset:update")
    public R update(@RequestBody VolSetEntity volSet){
			volSetService.updateById(volSet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:volset:delete")
    public R delete(@RequestBody Long[] ids){
			volSetService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
