package com.yunzhitx.medical.controller.utils.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**

 * @Description: TODO
 * @date 2017/11/21$ 10:05$
 */
@Component
@ConfigurationProperties("auth.jwt")
public class JwtConfigProperties {
    private String secret;
    /**
     * 秒钟
     */
    private int expiration = 60 * 60 * 24;
    private String header = "Authorization";
    private String tokenHead;
    private String parameterToken;
    private List<String> authenticationUrls;
    private List<String> authenticationExcludeUrls;

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public String getParameterToken() {
        return parameterToken;
    }

    public void setParameterToken(String parameterToken) {
        this.parameterToken = parameterToken;
    }

    public List<String> getAuthenticationUrls() {
        return authenticationUrls;
    }

    public void setAuthenticationUrls(List<String> authenticationUrls) {
        this.authenticationUrls = authenticationUrls;
    }

    public List<String> getAuthenticationExcludeUrls() {
        return authenticationExcludeUrls;
    }

    public void setAuthenticationExcludeUrls(List<String> authenticationExcludeUrls) {
        this.authenticationExcludeUrls = authenticationExcludeUrls;
    }
}
