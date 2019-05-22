package com.shengvideo.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.shengvideo.modules.sys.form.AddMsgForm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shengvideo.modules.sys.entity.RollMsgEntity;
import com.shengvideo.modules.sys.service.RollMsgService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-22 11:00:55
 */
@RestController
@RequestMapping("sys/rollmsg")
public class RollMsgController extends AbstractController{
    @Autowired
    private RollMsgService rollMsgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rollMsgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
			RollMsgEntity rollMsg = rollMsgService.selectById(id);

        return R.ok().put("rollMsg", rollMsg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:rollmsg:save")
    public R save(@RequestBody AddMsgForm addMsgForm){
            addMsgForm.getMsg().setCreateUser(getUserId());
			rollMsgService.save(addMsgForm.getDids(),addMsgForm.getMsg());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RollMsgEntity rollMsg){
			rollMsgService.updateById(rollMsg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
			rollMsgService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
