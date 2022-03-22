package com.yunzhitx.medical.controller.utils.jwt;


import java.util.List;

/**

 */
public class LoginUser {
    private Integer userId;
    private String userName;
    private String iccid;
    private String email;
    private String phone;
    private String terminal;
    /**
     * 授权类型
     */
    private String grantType = "password";
    /**
     * 授权范围，待用
     */
    private List<String> scope;

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getGrantType() {
        return grantType;
    }

    public LoginUser setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public LoginUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public LoginUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public LoginUser setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTerminal() {
        return terminal;
    }

    public LoginUser setTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }

    public List<String> getScope() {
        return scope;
    }

    public LoginUser setScope(List<String> scope) {
        this.scope = scope;
        return this;
    }
}
