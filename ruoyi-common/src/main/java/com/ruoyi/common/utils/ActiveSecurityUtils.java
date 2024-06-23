package com.ruoyi.common.utils;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginActiveUser;
import com.ruoyi.common.exception.ServiceException;

public class ActiveSecurityUtils {
    public static LoginActiveUser getLoginActiveUser()
    {
        try
        {
            return (LoginActiveUser) SecurityUtils.getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }
}
