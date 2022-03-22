package com.code.create.util;

/**
 * @Description:实体封装
 * @Createed Date: 2018/8/22-10:39
 **/
public class EntityBean {
    /**
     * 主键
     */
    private String keyName;
    /**
     * 访问权限 ---private、public
     */
    private String accessAuth;
    /**
     * 数据类型
     */
    private String type;
    /**
     * 实体名字
     */
    private String entityName;
    /**
     * 属性名称(首字母小写)
     */
    private String propName;
    /**
     * 属性名称(首字母大写)
     */
    private String propNameUP;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 字段名称
     */
    private String filedName;
    /**
     * 字段描述
     */
    private String remarks;
    /***/
    private int length;
    /***/
    private String validateNull;

    public EntityBean() {
    }

    public EntityBean(String keyName, String accessAuth, String type,
                      String entityName, String propName, String propNameUP,
                      String tableName, String filedName, String remarks) {
        super();
        this.keyName = keyName;
        this.accessAuth = accessAuth;
        this.type = type;
        this.entityName = entityName;
        this.propName = propName;
        this.propNameUP = propNameUP;
        this.tableName = tableName;
        this.filedName = filedName;
        this.remarks = remarks;
    }

    public EntityBean(String keyName, String accessAuth, String type,
                      String entityName, String propName, String propNameUP,
                      String tableName, String filedName, String remarks, int length, String validateNull) {
        super();
        this.keyName = keyName;
        this.accessAuth = accessAuth;
        this.type = type;
        this.entityName = entityName;
        this.propName = propName;
        this.propNameUP = propNameUP;
        this.tableName = tableName;
        this.filedName = filedName;
        this.remarks = remarks;
        this.length = length;
        this.validateNull = validateNull;
    }

    public String getAccessAuth() {
        return accessAuth;
    }

    public void setAccessAuth(String accessAuth) {
        this.accessAuth = accessAuth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getPropNameUP() {
        return propNameUP;
    }

    public void setPropNameUP(String propNameUP) {
        this.propNameUP = propNameUP;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValidateNull() {
        return validateNull;
    }

    public void setValidateNull(String validateNull) {
        this.validateNull = validateNull;
    }
}