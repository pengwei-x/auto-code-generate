package com.yunzhitx.medical.controller.utils.jwt;

import lombok.Data;

/**

 */
public class CurrentUser {
    private static ThreadLocal<CurrentUserVo> threadLocal = new ThreadLocal<>();

    private CurrentUser() {
    }

    public static Integer getUserId() {
        return getCurrentUserVo().getUserId();
    }
    public static String getIcccId() {
        return getCurrentUserVo().getIccid();
    }

    public static void setUserId(Integer userId) {
        getCurrentUserVo().setUserId(userId);
    }
    public static void setIccId(String iccid) {
        getCurrentUserVo().setIccid(iccid);
    }

    public static void setLoginUser(LoginUser loginUser) {
        getCurrentUserVo().setLoginUser(loginUser);
    }

    public static void getLoginUser() {
        getCurrentUserVo().getLoginUser();
    }

    /**
     * 重置是否多租户处理SQL状态
     */
    public static void resetNeedTenantSQL() {
        getCurrentUserVo().setNeedTenantSQL(true);
    }

    public static boolean isNeedTenantSQL() {
        return getCurrentUserVo().isNeedTenantSQL();
    }

    /**
     * 注意：默认为true，如果需要不做多租户处理SQL，在查询前设置后为false，查询执行完后需要自行重设为true
     *
     * @param needTenantSQL
     */
    public static void setNeedTenantSQL(boolean needTenantSQL) {
        getCurrentUserVo().setNeedTenantSQL(needTenantSQL);
    }

    public static String getTenantFlag() {
        return getCurrentUserVo().getTenantFlag();
    }

    public static void setTenantFlag(String tenantFlag) {
        getCurrentUserVo().setTenantFlag(tenantFlag);
    }

    public static boolean isSkipCommonTokenFilter() {
        return getCurrentUserVo().isSkipCommonTokenFilter();
    }

    public static void setSkipCommonTokenFilter(boolean skipCommonTokenFilter) {
        getCurrentUserVo().setSkipCommonTokenFilter(skipCommonTokenFilter);
    }

    public static boolean isNeedAuth() {
        return getCurrentUserVo().isNeedAuth();
    }

    public static void setNeedAuth(boolean needAuth) {
        getCurrentUserVo().setNeedAuth(needAuth);
    }

    private static CurrentUserVo getCurrentUserVo() {
        CurrentUserVo v = threadLocal.get();
        if (v == null) {
            v = new CurrentUserVo();
            threadLocal.set(v);
        }
        return v;
    }

    public static void clear() {
        threadLocal.remove();
    }

    @Data
    static class CurrentUserVo {
        private Integer userId;
        private String iccid;
        /**
         * 多租户标识
         */
        private String tenantFlag;
        private boolean needAuth;
        /**
         * 是否需要多租户SQL处理
         */
        private boolean needTenantSQL = true;
        /**
         * 网关使用自己，这里提供跳过共通的token验证
         */
        private boolean skipCommonTokenFilter = false;


        private LoginUser loginUser;
    }

}
