package com.ruoyi.active.controller;

import com.ruoyi.active.service.IActiveUserService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.ActiveUser;
import com.ruoyi.common.core.domain.model.LoginActiveUserBody;
import com.ruoyi.common.utils.ActiveSecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api(tags = "05-登陆管理", value = "活动模块中的的账号登陆API")
@RestController
@RequestMapping("/active/user/web")
public class ActiveUserWebController {
    @Autowired
    private IActiveUserService activeUserService;

    @ApiOperation(value = "用户登录", notes = "用户前端进行登陆操作")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginActiveUserBody loginActiveUserBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = activeUserService.login(loginActiveUserBody.getUsername(), loginActiveUserBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    @ApiOperation(value = "获取信息", notes = "通过token获取登陆用户信息")
    @GetMapping("/userInfo")
    public AjaxResult userInfo() {
        ActiveUser activeUser = ActiveSecurityUtils.getLoginActiveUser().getActiveUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("activeUser", activeUser);
        return ajax;
    }
}
