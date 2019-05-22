package com.shengvideo.modules.sys.controller;

import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;
import com.shengvideo.modules.sys.entity.MaterialClassEntity;
import com.shengvideo.modules.sys.service.MaterialClassService;
import io.netty.handler.codec.compression.FastLzFrameEncoder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;




/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
@RestController
@RequestMapping("sys/materialclass")
public class MaterialClassController extends AbstractController{
    @Autowired
    private MaterialClassService materialClassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:material:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = materialClassService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/l")
    @RequiresPermissions("sys:material:list")
    public R notNull(Integer id){
        if (id == null){
            id = 0;
        }
        return R.ok().put("data", getNodes(id,false));
    }

    @RequestMapping("/findSelfAndSon")
    @RequiresPermissions("sys:material:list")
    public R findSelfAndSon(Integer id){
        if (id == null){
            id = 0;
        }
        Map params  = new HashMap();
        params.put("pid",id);
        List<MaterialClassEntity> groups = materialClassService.selectByMap(params);
        if(id != 0){
           groups.add(materialClassService.selectById(id)) ;
        }
        for (MaterialClassEntity g:groups){
            g.setChildren(getNodes(g.getId(),false));
            if (g.getChildren().size() == 0 && !false){
                g.setChildren(null);
            }
        }
        return R.ok().put("data", groups);
    }

    private List<MaterialClassEntity> getNodes(Integer pid, boolean notNull){
        Map params  = new HashMap();
        params.put("pid",pid);
        List<MaterialClassEntity> groups = materialClassService.selectByMap(params);

        for (MaterialClassEntity g:groups){
            g.setChildren(getNodes(g.getId(),notNull));
            if (g.getChildren().size() == 0 && !notNull){
                g.setChildren(null);
            }
        }
        return groups;
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:material:info")
    public R info(@PathVariable("id") Integer id){
			MaterialClassEntity materialClass = materialClassService.selectById(id);

        return R.ok().put("materialClass", materialClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:material:save")
    public R save(@RequestBody MaterialClassEntity materialClass){
        materialClass.setCreatUser(getUserId());
        materialClass.setCreatTime(new Date());
        materialClassService.insert(materialClass);
        return R.ok().put("data",materialClass);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:material:update")
    public R update(@RequestBody MaterialClassEntity materialClass){
			materialClassService.updateById(materialClass);
        return R.ok().put("data",materialClass);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:material:delete")
    public R delete(@RequestBody Integer[] ids){
			materialClassService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    @RequestMapping("/delete/{id}")
    @RequiresPermissions("sys:material:delete")
    public R delete(@PathVariable("id") Integer id){
        materialClassService.deleteById(id);
        return R.ok();
    }
}
