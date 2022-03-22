package com.code.create.springboot;

import com.code.create.util.EntityBean;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Baorui.Zhang
 * @Description: 代码生成器,
 * 表名小写、可以加下划线！
 * 字段名以“fd_”开头,然后驼峰命名,
 * 每张表都有fd_id,fd_createDate,fd_updateDate,fd_reserved1，fd_reserved2
 * @date 2017/10/24 下午4:38
 */
public class MgrCodeGenerator {

    public static void genJavaFile(Map<String, List<EntityBean>> map, Configuration cfg, String ftlFileName, String javaFileName, String savePathPre) throws IOException {
        if (null != map && map.size() > 0) {
            //遍历所有的表
            for (Map.Entry<String, List<EntityBean>> entry1 : map.entrySet()) {
                //文件存放路径（SAVE_PATH_PRE+表名首字母小写的文件夹）
                String key = entry1.getKey();
                System.out.println(key);
                if (key.isEmpty()) {
                    continue;
                }
                final String entityNameLow = key.substring(0, 1).toLowerCase() + key.substring(1, key.length());

                String moduleName = entry1.getValue().get(0).getTableName().split("_")[0];
                String savePath = savePathPre + File.separator;

                if (javaFileName.contains("Impl")) {
                    //如果是impl 就放入impl
                    savePath = savePath + "impl" + File.separator;
                }
                // entity包则把po和dto放在一起
                if (savePath.contains("entity")) {
                    String[] split = javaFileName.split(".java");
                    if (split.length == 0) {
                        savePath = savePath + "po" + File.separator;
                    } else {
                        savePath = savePath + split[0].toLowerCase() + File.separator;
                    }
                }

                //需要生产的文件名
                String filePath = savePath + key + javaFileName;

                //创建放文件的文件夹
                File newsDir = new File(savePath);
                if (!newsDir.exists()) {
                    newsDir.mkdirs();
                }

                //如果文件存在，先删除
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }

                //获取模板
                Template temEntity = cfg.getTemplate(ftlFileName);

                Map<String, Object> root = new HashMap<>(10);
                root.put("entityName", key);
                root.put("beans", entry1.getValue());
                root.put("tableName", entry1.getValue().get(0).getTableName());
                root.put("packageName", Config.BASE_PACKAGE);
                root.put("moduleName", moduleName);
                root.put("entityNameLow", entityNameLow);
                root.put("entityKey", entry1.getValue().get(0).getKeyName());
                root.put("entityKeyFieldName", entry1.getValue().get(0).getFiledName());
                try {
                    Writer out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
                    temEntity.process(root, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param driver
     * @param url
     * @param user
     * @param password
     * @return Map<Key Value> Key是表名 value是表中字段的封装
     */
    public static Map<String, List<EntityBean>> getDatas(String driver, String url, String user, String password) {
        Map<String, List<EntityBean>> map = new HashMap<>(10);
        Map<String, List<String>> tables = getTables(driver, url, user, password);
        if (null != tables && tables.size() > 0) {
            Iterator<Map.Entry<String, List<String>>> iter = tables.entrySet().iterator();
            while (iter.hasNext()) {
                // 去除表明和字段的下划线、首字母大写
                //	EntityBean eb = new EntityBean();
                Map.Entry<String, List<String>> entry = iter.next();
                // 实体名称
                String entityName = "";
                String keyName = "";
                String tableName = entry.getKey();
                String[] keys = entry.getKey().split("_");

                for (int i = 0, len = keys.length; i < len; i++) {
                    if (i == 0 |i==1) {
                        continue;
                    }
                    String k = keys[i];
                    /***生成实体名字**/
                    entityName += k.substring(0, 1).toUpperCase() + k.substring(1, k.length());
                }
                /***生成属性名字**/
                List<String> list = entry.getValue();
                List<EntityBean> beanlist = new ArrayList<EntityBean>();
                // 表字段生成属性,字段格式--字段名:是否自增:数据类型,分隔符-->":"
                for (int m = 0; m < list.size(); m++) {
                    String str = list.get(m);
                    String[] string = str.split(":");
                    // 字段名称
                    String filedName = string[0];
                    // 数据类型
                    String type = "";
                    // 数据类型
                    String dateType = string[2];
                    if (null != filedName) {
                        if ("fd_create_Date".equals(filedName) || "fd_update_Date".equals(filedName) || "fd_reserved1".equals(filedName) || "fd_reserved2".equals(filedName)) {
//                            continue;
                        }
                    }
                    int length = 0;
                    try {
                        length = Integer.valueOf(string[4]);
                    } catch (Exception e) {
                    }
                    // 属性名称(首字母小写)
                    String propName = "";
                    // 属性名称(首字母大写)
                    String propNameUP = "";
                    String[] fNames = filedName.split("_");
                    String p;
                    if (fNames.length < 2) {
                        p = fNames[0];
                    } else {
                        p = fNames[1];
                        System.out.println("heh:" + fNames.length);

                        for (int i = 2; i < fNames.length; i++) {
                            System.out.println(fNames[i]);
                            p += fNames[i].substring(0, 1).toUpperCase() + fNames[i].substring(1);
                        }

                    }
//                    String p = fNames[1];
                    propName = p;
                    propNameUP = p.substring(0, 1).toUpperCase() + p.substring(1);
                    type = getType(Integer.valueOf(dateType));
                    if (m == 0) {
                        keyName = propName;
                    }
                    String remark = "";
                    try {
                        remark = string[3];
                    } catch (Exception e) {
                    }
                    beanlist.add(new EntityBean(keyName, "private", type, entityName, propName, propNameUP, tableName, filedName, remark, length, ""));
                    System.out.println(keyName + " " + type + " " + entityName+"PO" + " " + propName + "   " + propNameUP + " " + tableName + " " + filedName + " " + length + "" + remark);
                }
                map.put(entityName, beanlist);
                //}
            }
        }
        return map;
    }

    public static String getType(Integer type) {
        String typeName = "";
        switch (type) {
            case Types.BIGINT:
                typeName = "Long";
                break;
            case Types.SMALLINT:
            case Types.TINYINT:
            case Types.INTEGER:
                typeName = "Integer";
                break;
            case Types.BOOLEAN:
            case Types.BIT:
                typeName = "Boolean";
                break;
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.LONGNVARCHAR:
                typeName = "String";
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
            case Types.TIME:
                typeName = "Date";
                break;
            case Types.DOUBLE:
            case Types.DECIMAL:
                typeName = "Double";
                break;
            case Types.FLOAT:
                typeName = "Float";
                break;
            default:
        }
        return typeName;
    }

    /**
     * 获取数据库中数据的表和字段
     *
     * @param driver
     * @param url
     * @param user
     * @param password
     * @return 返回一个map，key是表名，value是字段列表，其中字段格式: 字段名:是否自增:数据类型  ex: ename:YES:-5
     * 字段数据类型是封装成的一个类，值是数字类型，有可能有负值(枚举类型)
     */
    public static Map<String, List<String>> getTables(String driver, String url, String user, String password) {
        try {
            Map<String, List<String>> map = new HashMap<>(10);
            Class.forName(driver);
            // 获取链接
            Connection connection = DriverManager.getConnection(url, user, password);
            String currentCatalog = connection.getCatalog();
            ResultSet resultSet = connection.getMetaData().getTables(currentCatalog, null, null, new String[]{"TABLE"});
            ResultSet rs = null;
            while (resultSet.next()) {
                String tableName = resultSet.getObject(3).toString();
//                if (!Config.TABLE_NAME_FILTER.test(tableName)) {
//                    continue;
//                }
                if (!tableName.contains(Config.TABLE_PATTERN)){
                    continue;
                }
                rs = connection.getMetaData().getColumns(currentCatalog, null, tableName, null);
                List<String> list = new ArrayList<String>();
                while (rs.next()) {
                    list.add(rs.getString(4) + ":" + rs.getString("IS_AUTOINCREMENT") + ":" + rs.getString("DATA_TYPE") + ":" + rs.getString("REMARKS") + ":" + rs.getString("COLUMN_SIZE"));
                }
                map.put(tableName, list);
            }
            rs.close();
            resultSet.close();
            connection.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
