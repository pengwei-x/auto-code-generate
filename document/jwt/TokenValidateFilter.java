package com.yunzhitx.medical.controller.utils.jwt;

import com.alibaba.fastjson.JSON;
import com.yunzhitx.medical.common.InvokeResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 服务接口token验证
 * <p>和网关的验证不一样,这里只做token自校验</p>
 *

 */
@Order(1)
@Component
public class TokenValidateFilter extends OncePerRequestFilter {
    private static PathMatcher matcher = new AntPathMatcher();

    private JwtTokenProvider jwtTokenProvider;
    private JwtConfigProperties jwtConfigProperties;

    public TokenValidateFilter(JwtTokenProvider jwtTokenProvider,
                               JwtConfigProperties jwtConfigProperties) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtConfigProperties = jwtConfigProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers",
//                "origin, content-type, accept, x-requested-with, sid, mycustom, smuser");
        if (CurrentUser.isSkipCommonTokenFilter()) {
            filterChain.doFilter(request, response);
            return;
        }
        CurrentUser.clear();
        String uri = request.getRequestURI();
        String token = getJwtToken(request);
        UaaClaims claims = jwtTokenProvider.getClaims(token);
        if (isNeedValidate(uri)) {
            CurrentUser.setNeedAuth(true);
            if (claims == null) {
                sendNoPermission(response);
                return;
            }
        }

        if (claims != null) {
            CurrentUser.setUserId(Integer.parseInt(claims.getUserId()));
            CurrentUser.setIccId(claims.getICCID());
            CurrentUser.setLoginUser(claims.getLoginUser());
        }

        filterChain.doFilter(request, response);
        CurrentUser.clear();
    }

    protected void sendNoPermission(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json");

        PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(InvokeResult.unauthorized()));
        writer.close();
    }

    protected boolean isNeedValidate(String uri) {
        List<String> excludeUrls = jwtConfigProperties.getAuthenticationExcludeUrls();
        List<String> patterns = jwtConfigProperties.getAuthenticationUrls();

        return (!match(uri, excludeUrls)) && match(uri, patterns);
    }

    protected boolean match(String uri, List<String> patterns) {
        if (patterns != null) {
            for (String pattern : patterns) {
                if (matcher.match(pattern, uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected String getJwtToken(HttpServletRequest request) {
        String token = null;
        String pname = jwtConfigProperties.getParameterToken();
        if (pname != null && !"".equals(pname)) {
            token = request.getParameter(pname);
        }
        if (token == null) {
            token = request.getHeader(jwtConfigProperties.getHeader());
        }
        token = subToken(token);
        return token;
    }

    protected String subToken(String orgToken) {
        if (orgToken != null
                && jwtConfigProperties.getTokenHead() != null
                && orgToken.startsWith(jwtConfigProperties.getTokenHead())) {
            // The part after "Bearer "
            orgToken = orgToken.substring(jwtConfigProperties.getTokenHead().length());
        }
        return orgToken;
    }
}
