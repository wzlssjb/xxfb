package com.shengvideo.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.shengvideo.modules.sys.form.AddPlayForm;
import com.shengvideo.modules.sys.form.MobilePushForm;
import com.shengvideo.modules.sys.form.TextPushForm;
import com.shengvideo.modules.sys.form.ThemeOffForm;
import com.shengvideo.modules.sys.service.PublishDeviceService;
import com.shengvideo.modules.sys.service.PublishPlayTimeService;
import com.shengvideo.modules.sys.service.ThemeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shengvideo.modules.sys.entity.PublishEntity;
import com.shengvideo.modules.sys.service.PublishService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * publish;发布主表
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
@RestController
@RequestMapping("sys/publish")
public class PublishController extends AbstractController{
    @Autowired
    private PublishService publishService;
    @Autowired
    private PublishDeviceService publishDeviceService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private PublishPlayTimeService playTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:publish:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:publish:info")
    public R info(@PathVariable("id") Long id){
        PublishEntity publish = publishService.selectById(id);
        return R.ok().put("publish", publish);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:publish:save")
    public R save(@RequestBody PublishEntity publish){
        publish.setUid(getUserId());
        publishService.insert(publish);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:publish:update")
    public R update(@RequestBody PublishEntity publish){
			publishService.updateById(publish);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:publish:delete")
    public R delete(@RequestBody Long[] ids){
			publishService.deleteByIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/device/{id}")
    @RequiresPermissions("sys:publish:list")
    public R device(@PathVariable("id") Long id){
        return R.ok().put("list",publishDeviceService.findByPid(id));
    }

    @RequestMapping("/theme/{id}")
    @RequiresPermissions("sys:publish:list")
    public R theme(@PathVariable("id") Long id){
        return R.ok().put("list",themeService.findByPublish(id));
    }

    @RequestMapping("/times/{id}")
    @RequiresPermissions("sys:publish:list")
    public R times(@PathVariable("id") Long id){
        return R.ok().put("list",playTimeService.findByPid(id));
    }

    @RequestMapping("/theme/off")
    public R theme(@RequestBody ThemeOffForm form){
        publishDeviceService.offLine(form.getDids(),form.getPid());
        return R.ok();
    }

    @RequestMapping("/add-play")
    public R addPlay(@RequestBody AddPlayForm form){
        publishService.addPlay(form,getUserId());
        return R.ok();
    }

    @RequestMapping("/mobile-push")
    public R mobilePush(@RequestBody MobilePushForm form){
        publishService.mobilePush(form,getUserId());
        return R.ok();
    }

    @RequestMapping("/text-push")
    public R textPush(@RequestBody TextPushForm form){
        publishService.pushText(form.getElement(),form.getDids(),getUserId());
        return R.ok();
    }

    @RequestMapping("/time-range/{did}")
    public R timeRange(@PathVariable("did") Long did){
        return R.ok().put("list",publishService.findTodayTimeRange(did));
    }

    @RequestMapping("/clear-all")
    @RequiresPermissions("sys:publish:delete")
    public R clearAll(@RequestBody Long[] ids){
        publishDeviceService.clearAll(ids);
        return R.ok();
    }

    @RequestMapping("/clear-mobile")
    @RequiresPermissions("sys:publish:delete")
    public R clearMobile(@RequestBody Long[] ids){
        publishDeviceService.clearMobile(ids);
        return R.ok();
    }
}
