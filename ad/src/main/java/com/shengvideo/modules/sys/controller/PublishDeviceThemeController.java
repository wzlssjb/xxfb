package com.shengvideo.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.shengvideo.modules.sys.form.SubPlayForm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shengvideo.modules.sys.entity.PublishDeviceThemeEntity;
import com.shengvideo.modules.sys.service.PublishDeviceThemeService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-12 13:22:37
 */
@RestController
@RequestMapping("sys/publishdevicetheme")
public class PublishDeviceThemeController {
    @Autowired
    private PublishDeviceThemeService publishDeviceThemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:publish:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishDeviceThemeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:publish:info")
    public R info(@PathVariable("id") Long id){
			PublishDeviceThemeEntity publishDeviceTheme = publishDeviceThemeService.selectById(id);

        return R.ok().put("publishDeviceTheme", publishDeviceTheme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:publish:save")
    public R save(@RequestBody PublishDeviceThemeEntity publishDeviceTheme){
			publishDeviceThemeService.insert(publishDeviceTheme);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:publish:update")
    public R update(@RequestBody PublishDeviceThemeEntity publishDeviceTheme){
			publishDeviceThemeService.updateById(publishDeviceTheme);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:publish:delete")
    public R delete(@RequestBody Long[] ids){
			publishDeviceThemeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/sub-play")
    public R delete(@RequestBody SubPlayForm form){
        publishDeviceThemeService.subPlay(form.getDids(),form.getTids());
        return R.ok();
    }

}
