package com.shengvideo.modules.sys.service.impl;

import com.shengvideo.modules.sys.entity.*;
import com.shengvideo.modules.sys.service.ScountMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;

import com.shengvideo.modules.sys.dao.ScountThemeDao;
import com.shengvideo.modules.sys.service.ScountThemeService;


@Service("scountThemeService")
public class ScountThemeServiceImpl extends ServiceImpl<ScountThemeDao, ScountThemeEntity> implements ScountThemeService {

    @Autowired
    private ScountMaterialService scountMaterialService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<ScountThemeEntity> page = this.selectPage(
//                new Query<ScountThemeEntity>(params).getPage(),
//                new EntityWrapper<ScountThemeEntity>()
//        );
//
//        return new PageUtils(page);

        Page<ScountThemeEntity> page = new Query<ScountThemeEntity>(params).getPage();
        return  new PageUtils(page.setRecords(baseMapper.list(page,params)));
    }

    @Override
    public PageUtils queryPageTheme(Map<String, Object> params) {
//        Page<ScountThemeEntity> page = this.selectPage(
//                new Query<ScountThemeEntity>(params).getPage(),
//                new EntityWrapper<ScountThemeEntity>()
//        );
//
//        return new PageUtils(page);

        Page<ScountThemeEntity> page = new Query<ScountThemeEntity>(params).getPage();
        return  new PageUtils(page.setRecords(baseMapper.listByTheme(page,params)));
    }



    @Override
    public void saveCountTheme(ThemeEntity themeEntity,long stbId) {
        //切换广告   保存节目统计
                ScountThemeEntity scountThemeEntity = new ScountThemeEntity(themeEntity.getId(),stbId,new Date());
                insert(scountThemeEntity);
                List<ThemePageEntity> pages = themeEntity.getPages();
                if(pages != null && pages.size() > 0){
                     for(ThemePageEntity pageEntity :pages){
                           List<ElementEntity> elementEntities = pageEntity.getElements();
                           if(elementEntities != null && elementEntities.size() > 0){
                               for(ElementEntity element:elementEntities){
                                   if(element.getMid() != null){
                                       ScountMaterialEntity materialEntity = new ScountMaterialEntity(element.getMid(),stbId,new Date());
                                       scountMaterialService.insert(materialEntity);
                                   }
                               }
                           }
                     }
                }

        }
}
