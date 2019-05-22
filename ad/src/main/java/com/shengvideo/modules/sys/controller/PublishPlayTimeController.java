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

import com.shengvideo.modules.sys.entity.PublishPlayTimeEntity;
import com.shengvideo.modules.sys.service.PublishPlayTimeService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 播放时间段;
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@RestController
@RequestMapping("sys/publishplaytime")
public class PublishPlayTimeController {
    @Autowired
    private PublishPlayTimeService publishPlayTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:publishplaytime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishPlayTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:publishplaytime:info")
    public R info(@PathVariable("id") Long id){
			PublishPlayTimeEntity publishPlayTime = publishPlayTimeService.selectById(id);

        return R.ok().put("publishPlayTime", publishPlayTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:publishplaytime:save")
    public R save(@RequestBody PublishPlayTimeEntity publishPlayTime){
			publishPlayTimeService.insert(publishPlayTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:publishplaytime:update")
    public R update(@RequestBody PublishPlayTimeEntity publishPlayTime){
			publishPlayTimeService.updateById(publishPlayTime);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:publishplaytime:delete")
    public R delete(@RequestBody Long[] ids){
			publishPlayTimeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
