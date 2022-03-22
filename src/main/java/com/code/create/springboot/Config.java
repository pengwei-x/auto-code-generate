package com.code.create.springboot;

import java.util.function.Predicate;

/**
 * @author Baorui.Zhang
 */
public class Config {
    /**
     * 保存文件路径(工作空间)
     */
    public static final String SAVE_PATH_PRE = "/Users/pengwei/Documents/work/wisdom/temp";
    /**
     * 项目名
     */
    public static final String PROJECT_NAME = "itown";
    /**
     * 组名
     * <groupId>${groupId}</groupId>
     */
    public static final String GROUP_ID = "";
    /**
     * 基础包名
     */
    public static final String BASE_PACKAGE = "";

    /**
     * 数据库配置
     */
    public static final String URL = "jdbc:mysql://101.206.8.21:8306/db_itown_oa?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8";
    public static final String USER = "itownoa";
    public static final String PASSWORD = "itownOa@2022!";
    /**
     * 根据表生产相应文件（未设置即全部）
     */
//    public static final Predicate<String> TABLE_NAME_FILTER = Predicate.isEqual("t_oa_area");

    public static final String TABLE_PATTERN="t_oa_";
    /**
     * 是否生成common
     */
    public static final boolean IGNORE_BASE_CODE = false;

}
