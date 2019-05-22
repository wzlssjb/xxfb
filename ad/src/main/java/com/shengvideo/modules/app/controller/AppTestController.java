package com.shengvideo.modules.app.controller;


import com.shengvideo.common.utils.R;
import com.shengvideo.modules.app.annotation.Login;
import com.shengvideo.modules.app.annotation.LoginUser;
import com.shengvideo.modules.app.entity.UserEntity;

import com.shengvideo.modules.sys.service.ThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * APP测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {
    @Autowired
    private ThemeService themeService;
    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }
    @GetMapping("/list/{did}")
    public R onTime(@PathVariable("did") Long did) {
        return R.ok().put("list",themeService.findThemeByDeviceOnTime(did));
    }

}
