package com.shengvideo.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.shengvideo.modules.sys.entity.SgroupEntity;
import com.shengvideo.modules.sys.service.SgroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;



/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@RestController
@RequestMapping("generator/sgroup")
public class SgroupController extends AbstractController{
    @Autowired
    private SgroupService sgroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:stb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sgroupService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/goupList")
    @RequiresPermissions("generator:stb:list")
    public R notNull(){
        List<SgroupEntity> sgroupEntities = sgroupService.getNodes(0,true);
        SgroupEntity root = new SgroupEntity();
        root.setId(null);
        root.setChildren(sgroupEntities);
        root.setParentId(null);
        root.setName("全部");
        List<SgroupEntity> list = new ArrayList<>();
        list.add(root);
        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:stb:info")
    public R info(@PathVariable("id") Long id){
        SgroupEntity sgroup = sgroupService.selectById(id);

        return R.ok().put("sgroup", sgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:stb:save")
    public R save(@RequestBody SgroupEntity sgroup){
//            sgroup.setSysUserId(getUserId());
        if(sgroup.getParentId() == null){
            sgroup.setParentId(0l);
        }
			sgroupService.insert(sgroup);
        return R.ok().put("data",sgroup);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:stb:update")
    public R update(@RequestBody SgroupEntity sgroup){
			sgroupService.updateById(sgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:stb:delete")
    public R delete(@RequestBody Long[] ids){
			sgroupService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/delete/{id}")
    @RequiresPermissions("generator:stb:delete")
    public R delete(@PathVariable("id") long id){
        sgroupService.deleteById(id);
        return R.ok();
    }
}
