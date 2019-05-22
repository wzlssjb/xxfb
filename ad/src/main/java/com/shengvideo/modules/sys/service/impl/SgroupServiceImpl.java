package com.shengvideo.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.Query;
import com.shengvideo.modules.sys.dao.SgroupDao;
import com.shengvideo.modules.sys.entity.SgroupEntity;
import com.shengvideo.modules.sys.service.SgroupService;
import com.shengvideo.modules.sys.service.StbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("sgroupService")
public class SgroupServiceImpl extends ServiceImpl<SgroupDao, SgroupEntity> implements SgroupService {
    @Autowired
    StbService stbService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SgroupEntity> page = this.selectPage(
                new Query<SgroupEntity>(params).getPage(),
                new EntityWrapper<SgroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SgroupEntity> getNodes(long pid, boolean notNull) {
//        Map params  = new HashMap();
//        params.put("parent_id",pid);
        List<SgroupEntity> groups = baseMapper.queryByParent(pid);
        for (SgroupEntity g:groups){
            g.setChildren(getNodes(g.getId(),notNull));
            g.setStbCount(stbService.selectCountByGroup(g.getId()));
            if (g.getChildren().size() == 0 && !notNull){
                g.setChildren(null);
            }
        }
        return groups;
    }

//    @Override
//    public SgroupEntity queryByRoutId(Integer routeId) {
//        return baseMapper.queryByRoutId(routeId);
//    }


    @Override
    public void deleteGroupId(long id) {
//        释放终端
       stbService.updateDeleteGroupId(id);
//       删除终端以及底下的终端
        baseMapper.deleteGroupId(id);
    }
}
