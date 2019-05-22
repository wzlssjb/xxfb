package com.shengvideo.modules.sys.controller;

import java.util.*;

import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;
import com.shengvideo.modules.sys.entity.MaterialClassEntity;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import com.shengvideo.modules.sys.service.MaterialClassService;
import com.shengvideo.modules.sys.service.MaterialService;
import com.shengvideo.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-09-10 11:09:56
 */
@RestController
@RequestMapping("sys/material")
public class MaterialController extends AbstractController{
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialClassService classService;
    @Autowired
    SysConfigService configService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:material:list")
    public R list(@RequestParam Map<String, Object> params){
        //TODO 需要区分普通用户与超级用户
//        params.put("createUser",getUserId());
        PageUtils page = materialService.queryPage(params);
        String basePath = configService.getValue("source_server");
        for (Object material :page.getList()){
            MaterialEntity m = (MaterialEntity) material;
            m.setFilePath( basePath + m.getFilePath());
        }
        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:material:info")
    public R info(@PathVariable("id") Long id){
			MaterialEntity material = materialService.selectById(id);

        return R.ok().put("material", material);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:material:save")
    public R save(@RequestBody MaterialEntity material){
            material.setCreateUser(getUserId());
            material.setCreateTime(new Date());
            material.setCheckStatus(0);
			materialService.insert(material);
        return R.ok().put("mid",material.getId());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:material:update")
    public R update(@RequestBody MaterialEntity material){
			materialService.updateById(material);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:material:delete")
    public R delete(@RequestBody Long[] ids){
			materialService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/size")
    public R size(){
        Map map = new HashMap();
        map.put("pid",0);
        List<MaterialClassEntity> classes = classService.selectByMap(map);
        List<String> name = new ArrayList<>();
        List<Map> size = new ArrayList<>();
        for (MaterialClassEntity clazz : classes) {
            name.add(clazz.getName());
            Map value = new HashMap();
            value.put("name",clazz.getName());
            value.put("value",materialService.selectSizeByType(clazz.getId()) / 1000.0);
            size.add(value);
        }
        return R.ok().put("name",name).put("size",size);
    }


}
