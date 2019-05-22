package com.shengvideo.modules.sys.controller;

import com.shengvideo.common.exception.RRException;
import com.shengvideo.common.utils.ApkUtil;
import com.shengvideo.common.utils.Constant;
import com.shengvideo.common.utils.OfficeUtils;
import com.shengvideo.common.utils.R;
import com.shengvideo.modules.sys.entity.MaterialEntity;
import com.shengvideo.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/sys/")
public class UploadController {
    /**
     * 仅用于上传素材
     */
    @Autowired
    private SysConfigService configService;
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, String iconPath,Integer type) throws IOException {
        String root_path =  configService.getValue(Constant.ROOT_PATH);
        String vodDir = iconPath;
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = new Date().getTime() + fileType;
        String path =  vodDir + "/" + fileName;
        String savePath = root_path + path;
        File dir = new File( root_path + vodDir);
        dir.mkdirs();
        File newFile = new File(savePath);
        file.transferTo(newFile);
        R r = R.ok().put("url",path);
        r.put("size",file.getSize());
        if (type == MaterialEntity.APP){
            if (!fileType.toLowerCase().equals(".apk")){
                throw new RRException("文件格式有误,请检查后重新上传");
            }
            r.put("packageName", ApkUtil.getApkInfo(savePath));
        }
        if (type == MaterialEntity.IMG){
            BufferedImage sourceImg= ImageIO.read(new FileInputStream(newFile));
            r.put("width",sourceImg.getWidth());
            r.put("height",sourceImg.getHeight());
        }
        if (type == MaterialEntity.TEXT){
            String videoFile = vodDir + "/" + new Date().getTime() + ".swf";
//            if (fileType.toLowerCase().equals(".ppt") || fileType.toLowerCase().equals(".pptx") ){
//                videoFile += ".mp4";
//            }else {
//                videoFile += ".swf";
//            }
            if ( OfficeUtils.convert2Video(savePath,root_path + videoFile)){
                r.put("convertPath",videoFile);
            }else {
                throw new RRException("转换失败，请检查文件格式");
            }
        }
        return r;
    }
}
