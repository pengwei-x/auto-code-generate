package com.yunzhitx.medical.controller.utils.jwt;


import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.impl.JwtMap;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 对jwt的数据块进行扩展
 * <p>用户中心校验时,需要校验该token的创建时间必须在用户密码最后修改时间之后,以保证用户修改密码后原来的token失效</p>
 * <p>
 * iss: token的发行者
 * sub: token的题目
 * aud: token的客户
 * exp: 经常使用的，以数字时间定义失效期，也就是当前时间以后的某个时间本token失效。
 * nbf: 定义在此时间之前，JWT不会接受处理。
 * iat: JWT发布时间，能用于决定JWT年龄
 * jti: JWT唯一标识. 能用于防止 JWT重复使用，一次只用一个token
 *

 */
public class UaaClaims extends JwtMap implements Claims {
    public static final String SCOPE = "scope";
    public static final String USER_NAME = "userName";
    public static final String USER_ID = "userId";
    public static final String ICCID = "iccid";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String GRANT_TYPE = "grantType";
    public static final String TERMINAL = "terminal";
    public static final String ORG_DATA = "org_Data";
    private UaaClaims() {
    }
    public UaaClaims(LoginUser user) {
        if (user == null || user.getUserId() == null) {
            throw new RuntimeException("parameter is null.");
        }
//        UAAClaims uaaClaims = new UAAClaims();
        setIssuedAt(new Date());
        setIccId(user.getIccid());
        setAudience(String.valueOf(user.getUserId()));
        setId(UUID.randomUUID().toString());
        setSubject(String.valueOf(user.getUserId()));
        setNotBefore(new Date());
        setUserId(user.getUserId());
        setGrantType(user.getGrantType());
        setUserName(user.getUserName());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setTerminal(user.getTerminal());
        // scope
        setScope(user.getScope());

        put(ORG_DATA, JSON.toJSONString(user));
    }

    public static UaaClaims parse(Claims claims) {
        UaaClaims uaaClaims = new UaaClaims();
        uaaClaims.putAll(claims);
        return uaaClaims;
    }

    public String getOrgData() {
        return getString(ORG_DATA);
    }

    public LoginUser getLoginUser() {
        return JSON.parseObject(getOrgData(), LoginUser.class);
    }

    public List<String> getScope() {
        return get(SCOPE, List.class);
    }

    public void setScope(List<String> scope) {
        setValue(SCOPE, scope);
    }

    public String getGrantType() {
        return getString(GRANT_TYPE);
    }

    public void setGrantType(String grantType) {
        setValue(GRANT_TYPE, grantType);
    }

    public String getTerminal() {
        return getString(TERMINAL);
    }

    public void setTerminal(String terminal) {
        setValue(TERMINAL, terminal);
    }

    public String getUserName() {
        return getString(USER_NAME);
    }

    public void setUserName(String userName) {
        setValue(USER_NAME, userName);
    }

    public String getEmail() {
        return getString(EMAIL);
    }

    public void setEmail(String email) {
        setValue(EMAIL, email);
    }

    public String getPhone() {
        return getString(PHONE);
    }

    public void setPhone(String phone) {
        setValue(PHONE, phone);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(int userId) {
        setValue(USER_ID, userId);
    }
    public void setIccId(String iccid) {
        setValue(ICCID, iccid);
    }

    @Override
    public String getIssuer() {
        return getString(ISSUER);
    }

    public  String getICCID() {
        return getString(ICCID);
    }

    @Override
    public Claims setIssuer(String iss) {
        setValue(ISSUER, iss);
        return this;
    }

    @Override
    public String getSubject() {
        return getString(SUBJECT);
    }

    @Override
    public Claims setSubject(String sub) {
        setValue(SUBJECT, sub);
        return this;
    }

    @Override
    public String getAudience() {
        return getString(AUDIENCE);
    }

    @Override
    public Claims setAudience(String aud) {
        setValue(AUDIENCE, aud);
        return this;
    }

    @Override
    public Date getExpiration() {
        return get(Claims.EXPIRATION, Date.class);
    }

    @Override
    public Claims setExpiration(Date exp) {
        setDate(Claims.EXPIRATION, exp);
        return this;
    }

    @Override
    public Date getNotBefore() {
        return get(Claims.NOT_BEFORE, Date.class);
    }

    @Override
    public Claims setNotBefore(Date nbf) {
        setDate(Claims.NOT_BEFORE, nbf);
        return this;
    }

    @Override
    public Date getIssuedAt() {
        return get(Claims.ISSUED_AT, Date.class);
    }

    @Override
    public Claims setIssuedAt(Date iat) {
        setDate(Claims.ISSUED_AT, iat);
        return this;
    }

    @Override
    public String getId() {
        return getString(ID);
    }

    @Override
    public Claims setId(String jti) {
        setValue(Claims.ID, jti);
        return this;
    }

    @Override
    public <T> T get(String claimName, Class<T> requiredType) {
        Object value = get(claimName);
        if (value == null) {
            return null;
        }

        if (Claims.EXPIRATION.equals(claimName)
                || Claims.ISSUED_AT.equals(claimName)
                || Claims.NOT_BEFORE.equals(claimName)) {
            value = getDate(claimName);
        }

        if (requiredType == Date.class && value instanceof Long) {
            value = new Date((Long) value);
        }

        if (!requiredType.isInstance(value)) {
            throw new RequiredTypeException("Expected value to be of type: "
                    + requiredType + ", but was " + value.getClass());
        }

        return requiredType.cast(value);
    }

}