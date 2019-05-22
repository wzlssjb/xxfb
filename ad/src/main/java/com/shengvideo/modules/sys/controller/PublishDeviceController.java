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

import com.shengvideo.modules.sys.entity.PublishDeviceEntity;
import com.shengvideo.modules.sys.service.PublishDeviceService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 发布设备;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@RestController
@RequestMapping("sys/publishdevice")
public class PublishDeviceController {
    @Autowired
    private PublishDeviceService publishDeviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:publishdevice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishDeviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:publishdevice:info")
    public R info(@PathVariable("id") Long id){
			PublishDeviceEntity publishDevice = publishDeviceService.selectById(id);

        return R.ok().put("publishDevice", publishDevice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:publishdevice:save")
    public R save(@RequestBody PublishDeviceEntity publishDevice){
			publishDeviceService.insert(publishDevice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:publishdevice:update")
    public R update(@RequestBody PublishDeviceEntity publishDevice){
			publishDeviceService.updateById(publishDevice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:publishdevice:delete")
    public R delete(@RequestBody Long[] ids){
			publishDeviceService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
