package com.ruoyi.framework.security.filter;

import java.io.IOException;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.ActiveConstants;
import com.ruoyi.common.core.domain.model.LoginActiveUser;
import com.ruoyi.framework.web.service.ActiveTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Resource
    private ActiveTokenService activeTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (request.getRequestURI().contains(ActiveConstants.ACTIVE_URI)) {
            LoginActiveUser loginActiveUser = activeTokenService.getLoginActiveUser(request);
            if (StringUtils.isNotNull(loginActiveUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                activeTokenService.verifyToken(loginActiveUser);
                auth(loginActiveUser, loginActiveUser.getAuthorities(), request);
            }
        }
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            tokenService.verifyToken(loginUser);
            auth(loginUser, loginUser.getAuthorities(), request);
        }
        chain.doFilter(request, response);
    }

    /**
     * 验证token
     *
     * @param obj         登录信息
     * @param authorities 已授予的权限集合
     * @param request     网络请求
     */
    private void auth(Object obj, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(obj, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
