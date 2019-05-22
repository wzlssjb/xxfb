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

import com.shengvideo.modules.sys.entity.ThemeEntity;
import com.shengvideo.modules.sys.service.ThemeService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;


/**
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-14 18:31:02
 */
@RestController
@RequestMapping("sys/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:theme:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = themeService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/temp/list")
    @RequiresPermissions("sys:theme:list")
    public R temp(@RequestParam Map<String, Object> params) {
        PageUtils page = themeService.queryTempPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 信息
     */

    @RequestMapping("/info/{id}")
//    @RequiresPermissions("sys:theme:info")
    public R info(@PathVariable("id") Long id) {
        ThemeEntity theme = themeService.findById(id);

        return R.ok().put("theme", theme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:theme:save")
    public R save(@RequestBody ThemeEntity theme) {
        themeService.insert(theme);
        return R.ok().put("id",theme.getId());
    }

    /**
     * 保存
     */

    @RequestMapping("/list/{did}")
    @RequiresPermissions("sys:theme:save")
    public R onTime(@PathVariable("did") Long did) {
        return R.ok().put("list",themeService.findThemeByDeviceOnTime(did));
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:theme:update")
    public R update(@RequestBody ThemeEntity theme) {
        themeService.updateById(theme);
        return R.ok();
    }

    @RequestMapping("/updateinfo")
    @RequiresPermissions("sys:theme:update")
    public R updateInfo(@RequestBody ThemeEntity theme) {
        themeService.updateInfo(theme);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:theme:delete")
    public R delete(@RequestBody Long[] ids) {
        themeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/by-device")  //按设备获取节目
    @RequiresPermissions("sys:theme:delete")
    public R device(@RequestBody Long[] ids) {
        return R.ok().put("list",themeService.findByDevices(ids));
    }

}
