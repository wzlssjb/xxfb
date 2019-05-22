package com.shengvideo.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.modules.sys.entity.ElementEntity;
import com.shengvideo.modules.sys.entity.PublishEntity;
import com.shengvideo.modules.sys.form.AddPlayForm;
import com.shengvideo.modules.sys.form.MobilePushForm;
import com.shengvideo.modules.sys.vo.TimeRangOnPlayTheme;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * publish;发布主表
 *
 * @author wz
 * @email 798378318@qq.com
 * @date 2018-11-02 10:54:12
 */
public interface PublishService extends IService<PublishEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PublishEntity> findByNow(Map<String, Object> params);

    List<PublishEntity> findInterCut(Long did);

    List<PublishEntity> findnormal(Long did);

    List<PublishEntity> findInterCut(Long did, Date startTime, Date endTime);

    List<PublishEntity> findnormal(Long did, Date startTime, Date endTime);

    boolean checkOnDate(PublishEntity publish);

    boolean deleteByIds(List<Long> idList);

    boolean  deleteById(Long id);

    void addPlay(AddPlayForm form, Long uid);

    void mobilePush(MobilePushForm form, Long uid);

    void pushText (ElementEntity element, Long[] dids, Long uid);

    List<TimeRangOnPlayTheme> findTodayTimeRange(Long did);

    List<Date> findTimes(LocalDate date, Long did);
}

