package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.vo.OpVo;
import com.shengvideo.modules.sys.vo.RemoteInstallVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
public interface StbService extends IService<StbEntity> {

    PageUtils queryPage(Map<String, Object> params);

    StbEntity findByMAC(Map<String,Object> params);

    StbEntity findById(Long id);

    List<StbEntity> findByPublish(Long pid);

    boolean updateStb(StbEntity stbEntity);

    List<StbEntity> selectByRouteOnLine(Integer rid);

    int selectCountAll();

    int selectCountByOnline(boolean online);

    int selectCountBySwitch(boolean switchStatus);

    int selectCountByGroup(Long gid);

    void updateVol(Long[] dids, int vol);

    void updateInfo(StbEntity stbEntity);

    void remoteInstall(RemoteInstallVo vo);

    void initOnline();

    List<OpVo> resolution();

    void remoteBoot(RemoteInstallVo vo);

    void updateDeleteGroupId(Long pid);
}

