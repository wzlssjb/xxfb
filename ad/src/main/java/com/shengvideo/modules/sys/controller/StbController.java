package com.shengvideo.modules.sys.controller;

import com.shengvideo.common.exception.RRException;
import com.shengvideo.common.utils.ExcelUtil;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;
import com.shengvideo.modules.sys.entity.StbEntity;
import com.shengvideo.modules.sys.form.SetSwitchForm;
import com.shengvideo.modules.sys.form.SetVolForm;
import com.shengvideo.modules.sys.service.SgroupService;
import com.shengvideo.modules.sys.service.StbService;
import com.shengvideo.modules.sys.service.SwitchSetService;
import com.shengvideo.modules.sys.service.VolSetService;
import com.shengvideo.modules.sys.vo.OpVo;
import com.shengvideo.modules.sys.vo.RemoteInstallVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * 
 *
 * @author lmy
 * @email 2945828910@qq.com
 * @date 2018-09-11 13:55:24
 */
@RestController
@RequestMapping("generator/stb")
public class StbController {
    @Autowired
    private StbService stbService;

    @Autowired
    private SgroupService sgroupService;

    @Autowired
    private VolSetService volSetService;

    @Autowired
    private SwitchSetService switchSetService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:stb:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = stbService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/resolution")
    @RequiresPermissions("generator:stb:list")
    public R resolution(){
        List<OpVo> vo = stbService.resolution();
        return R.ok().put("list", stbService.resolution());
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:stb:info")
    public R info(@PathVariable("id") Long id){
        StbEntity stb = stbService.findById(id);
//        SgroupEntity sgroupEntity = sgroupService.selectById(stb.getStbGroupId());
        return R.ok().put("stb", stb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:stb:save")
    public R save(@RequestBody StbEntity stb){
        stbService.insert(stb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:stb:update")
    public R update(@RequestBody StbEntity stb){

        stbService.updateStb(stb);

        return R.ok();
    }

    @RequestMapping("/updateinfo")
    @RequiresPermissions("generator:stb:update")
    public R updateInfo(@RequestBody StbEntity stb){

        stbService.updateInfo(stb);

        return R.ok();
    }

    @RequestMapping("/updateStatus")
    @RequiresPermissions("generator:stb:update")
    public R updateStatus(@RequestBody Long id){
        StbEntity stb = stbService.findById(id);
        stb.setSwitchStatus(stb.getSwitchStatus() == 1?0:1);
        stbService.updateInfo(stb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:stb:delete")
    public R delete(@RequestBody Long[] ids){
			stbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 远程安装，卸载
     */
    @RequestMapping("/remoteInstall")
    @RequiresPermissions("generator:stb:list")
    public R remoteInstall(@RequestBody RemoteInstallVo vo){
        stbService.remoteInstall(vo);
        return R.ok();
    }
    /**
     * 远程关机，重启
     */
    @RequestMapping("/remoteBoot")
    @RequiresPermissions("generator:stb:list")
    public R remoteBoot(@RequestBody RemoteInstallVo vo){
        stbService.remoteBoot(vo);
        return R.ok();
    }


    @RequestMapping("/set-vol")
    @RequiresPermissions("generator:stb:save")
    public R setVol(@RequestBody SetVolForm form){
        stbService.updateVol(form.getDids(),form.getDefaultVol());
        volSetService.saveVolSets(form.getDids(),form.getVols());
        return R.ok();
    }

    @RequestMapping("/setSwitch")
    @RequiresPermissions("generator:stb:save")
    public R setSwitch(@RequestBody SetSwitchForm form){
        switchSetService.saveSwitchSet(form.getDids(),form.getSwitchSetEntities());
        return R.ok();
    }

    @RequestMapping("/count")
    @RequiresPermissions("generator:stb:save")
    public R count(){
        Map<String,Object> map = new HashMap<>();
        map.put("all",stbService.selectCountAll());
        map.put("onlineCount",stbService.selectCountByOnline(true));
        map.put("offlineCount",stbService.selectCountByOnline(false));
        map.put("failCount",stbService.selectCountBySwitch(false));
        return R.ok().put("data",map);
    }

    //  下载模版
    @RequestMapping("/dltmp")
    @RequiresPermissions("generator:stb:save")
    public void sub(HttpServletResponse response) throws IOException {
        InputStream is = ExcelUtil.excelModelbyClass(StbEntity.class,null,null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((new Date().getTime() + ".xls").getBytes(), "iso-8859-1"));
        response.setContentLength(is.available());
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        byte[] buff = new byte[8192];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("/lead")
    public R upload(@RequestParam("file") MultipartFile file,Long gid) {
        try {
            List<StbEntity> list = ExcelUtil.importExcel(StbEntity.class,file.getInputStream());
            for (StbEntity s:
                    list) {
                if (StringUtils.isBlank(s.getName())) {
                    s.setName(s.getMac());
                }
                s.setStbGroupId(gid);
            }
            stbService.insertBatch(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("导入失败，请检查文件格式");
        }
        return R.ok();
    }
}
