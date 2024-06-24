package com.ruoyi.active.controller;

import com.ruoyi.active.domain.modle.UpActiveUserPassword;
import com.ruoyi.active.service.IActiveUserService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.ActiveUser;
import com.ruoyi.common.core.domain.model.LoginActiveUser;
import com.ruoyi.common.core.domain.model.LoginActiveUserBody;
import com.ruoyi.common.utils.ActiveSecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.ActiveTokenService;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "05-登陆管理-客户端", value = "活动模块中的的客户端账号登陆API")
@RestController
@RequestMapping("/active/user/web")
public class ActiveUserLoginWebController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private IActiveUserService activeUserService;

    @Autowired
    private ActiveTokenService activeTokenService;

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

    @ApiOperation(value = "退出登陆",notes = "用户退出登陆")
    @PostMapping("/logout")
    public AjaxResult loginOut(HttpServletRequest request){
        LoginActiveUser loginActiveUser = activeTokenService.getLoginActiveUser(request);
        String token = loginActiveUser.getToken();
        if (StringUtils.isNotEmpty(token)){
            activeTokenService.delLoginUser(token);
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改密码",notes = "用户修改密码")
    @PostMapping("/update")
    public AjaxResult updatePassword(@RequestBody UpActiveUserPassword upActiveUserPassword){
        return toAjax(activeUserService.updateActiveUserPassword(upActiveUserPassword));
    }
}
