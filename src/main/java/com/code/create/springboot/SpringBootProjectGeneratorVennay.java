package com.code.create.springboot;

import com.code.create.util.EntityBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.File.separator;

/**
 * @Description: 自动生成springboot的MVC项目
 * @author Baorui.Zhang
 **/
public class SpringBootProjectGeneratorVennay {
    /**
     * 资源文件夹路径
     */
    private static final String RESOURCES_PATH = SpringBootProjectGeneratorVennay.class.getResource("/").getPath();

    /**
     * 创建项目
     */
    private void genProject() {
        try {
            //项目路径:保存文件路径\项目名
            File projectPath = new File(Config.SAVE_PATH_PRE + separator + Config.PROJECT_NAME);
            //如果项目路径存在则删除
            if (projectPath.exists()) {
                FileUtils.deleteDirectory(projectPath);
            }
            //创建项目文件
            projectPath.mkdirs();

            //pom模板文件地址
            String ftlFilePath = SpringBootProjectGeneratorVennay.class.getResource("/").getPath() + separator + "mvcpom";
            Configuration pomConfig = new Configuration(Configuration.VERSION_2_3_28);
            pomConfig.setDirectoryForTemplateLoading(new File(ftlFilePath));
            Template template = pomConfig.getTemplate("POM.ftl");
            //封装父module参数
            Map<String, Object> pomMap = new HashMap<>(4, 1);
            List<String> moduleList = new ArrayList<>(2);
//            moduleList.add(Config.PROJECT_NAME + "-" + "api");
            moduleList.add(Config.PROJECT_NAME + "-" + "web");
            pomMap.put("projectName", Config.PROJECT_NAME);
            pomMap.put("groupId", Config.GROUP_ID);
            pomMap.put("moduleList", moduleList);
            //创建
            createParentProject(projectPath, pomMap, template);
            //创建子module
//            String apiPath = generateProjectDir("api");
            String controllerPath = generateProjectDir("web");
            //创建common
            if (Config.IGNORE_BASE_CODE) {
                createCommon(controllerPath);
            }
            //生成配置文件
//            createRes("web");
            //代码生成
            String ftlFilePath1 = MgrCodeGenerator.class.getResource("/").getPath() + "vennay";
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setDirectoryForTemplateLoading(new File(ftlFilePath1));

            // 生成java配置文件
//            if (Config.IGNORE_BASE_CODE) {
//                createConfigFile(controllerPath, cfg);
//            }


            final String driver = "com.mysql.jdbc.Driver";
            //获取数据库表-字段数据（key:去除下划线，首字母大写的表名，value:字段列表）
            //生成module文件夹
            Map<String, List<EntityBean>> map = MgrCodeGenerator.getDatas(driver, Config.URL, Config.USER, Config.PASSWORD);

            createJavaFile(map, cfg, "Entity.ftl", "PO.java", Paths.get(controllerPath, "entity").toString());
//            createJavaFile(map, cfg, "DTO.ftl", "DTO.java", Paths.get(controllerPath, "entity").toString());
            createJavaFile(map, cfg, "Mapper.ftl", "Mapper.java", Paths.get(controllerPath, "mapper").toString());
            createJavaFile(map, cfg, "XmlMapper.ftl", "Mapper.xml", Paths.get(controllerPath.substring(0, controllerPath.indexOf("java")), "resources", "mapper").toString());
//            createJavaFile(map, cfg, "Manager.ftl","Manager.java",Paths.get(controllerPath, "manager").toString());
//            createJavaFile(map, cfg, "ManagerImpl.ftl","ManagerImpl.java",Paths.get(controllerPath, "manager").toString());
            createJavaFile(map, cfg, "Service.ftl", "Service.java", Paths.get(controllerPath, "service").toString());
            createJavaFile(map, cfg, "ServiceImpl.ftl", "ServiceImpl.java", Paths.get(controllerPath, "service").toString());
            createJavaFile(map, cfg, "CreateCommand.ftl", "CreateCommand.java", Paths.get(controllerPath, "web", "command").toString());
//            createJavaFile(map, cfg, "UpdateCommand.ftl", "UpdateCommand.java", Paths.get(controllerPath, "web", "command").toString());
//            createJavaFile(map, cfg, "Assembler.ftl", "Assembler.java", Paths.get(controllerPath, "web", "assembler").toString());
//            createJavaFile(map, cfg, "Rest.ftl", "Rest.java", Paths.get(controllerPath, "web", "rest").toString());
            createJavaFile(map, cfg, "VO.ftl", "DTO.java", Paths.get(controllerPath, "entity").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createConfigFile(String controllerPath, Configuration cfg) throws IOException {
        //生成BaseMapper
            /*Map mapBaseMapper = new HashMap(1);
            mapBaseMapper.put("packageName", Config.BASE_PACKAGE);
            Template temBaseMapper = cfg.getTemplate("BaseMapper.ftl");
            String pathBaseMapper = Paths.get(daoPath, "other").toString();
            String nameBaseMapper = "MyBatisBaseMapper.java";
            createFileByTemp(mapBaseMapper, temBaseMapper, pathBaseMapper, nameBaseMapper);*/
        //生成BaseEntity
//            Map mapBaseEntity=new HashMap(1);
//            mapBaseEntity.put("packageName",Config.BASE_PACKAGE);
//            Template baseEntityTemplate = cfg.getTemplate("BaseEntity.ftl");
//            String pathBaseEntity = Paths.get(entityPath, "base").toString();
//            String nameBaseEntity = "BaseEntity.java";
//            createFileByTemp(mapBaseEntity,baseEntityTemplate,pathBaseEntity,nameBaseEntity);

        //生成启动类**WebApplication
        Map mapWebApplication = new HashMap(1);
        mapWebApplication.put("packageName", Config.BASE_PACKAGE);
        Template temWebApplication = cfg.getTemplate("WebApplication.ftl");
        String pathWebApplication = Paths.get(controllerPath, "web").toString();
        String nameWebApplication = "WebApplication.java";
        createFileByTemp(mapWebApplication, temWebApplication, pathWebApplication, nameWebApplication);

        //生成swagger配置类**nameSwaggerConfiguration  (现在还没controller目录，所以得放生成启动类**WebApplication后面)
//        Map mapSwaggerConfiguration = new HashMap(1);
//        mapSwaggerConfiguration.put("packageName", Config.BASE_PACKAGE);
//        Template temSwaggerConfiguration = cfg.getTemplate("SwaggerConfiguration.ftl");
//        String pathSwaggerConfiguration = Paths.get(controllerPath, "controller", "config").toString();
//        String nameSwaggerConfiguration = "SwaggerConfiguration.java";
//        createFileByTemp(mapSwaggerConfiguration, temSwaggerConfiguration, pathSwaggerConfiguration, nameSwaggerConfiguration);

        //生成swagger拦截器配置类**
//        Map mapSwaggerBootstrapUiDemoApplication = new HashMap(1);
//        mapSwaggerBootstrapUiDemoApplication.put("packageName", Config.BASE_PACKAGE);
//        Template temSwaggerBootstrapUiDemoApplication = cfg.getTemplate("SwaggerBootstrapUiDemoApplication.ftl");
//        String pathSwaggerBootstrapUiDemoApplication = Paths.get(controllerPath, "controller", "config").toString();
//        String nameSwaggerBootstrapUiDemoApplication = "SwaggerBootstrapUiDemoApplication.java";
//        createFileByTemp(mapSwaggerBootstrapUiDemoApplication, temSwaggerBootstrapUiDemoApplication, pathSwaggerBootstrapUiDemoApplication, nameSwaggerBootstrapUiDemoApplication);

        //生成mybatisPlus配置类**
//        Map mapMyBatisPlusConfig = new HashMap(1);
//        mapMyBatisPlusConfig.put("packageName", Config.BASE_PACKAGE);
//        Template temMyBatisPlusConfig = cfg.getTemplate("MyBatisPlusConfig.ftl");
//        String pathMyBatisPlusConfig = Paths.get(controllerPath, "controller", "config").toString();
//        String nameMyBatisPlusConfig = "MyBatisPlusConfig.java";
//        createFileByTemp(mapMyBatisPlusConfig, temMyBatisPlusConfig, pathMyBatisPlusConfig, nameMyBatisPlusConfig);

        //生成公用类PageResult

    }

    private void createRes(String projModuleSuffixName) throws Exception {
        String projModuleName = Config.PROJECT_NAME + "-" + projModuleSuffixName.toLowerCase();
        //生成module文件夹
        File projModuleDir = new File(Config.SAVE_PATH_PRE + separator + Config.PROJECT_NAME + separator + projModuleName);
        File resDir = new File(projModuleDir + separator + "src" + separator + "main" + separator + "resources");
        String ftlFilePathCommon = MgrCodeGenerator.class.getResource("/").getPath() + "/res";
        Configuration cfgCom = new Configuration();
        cfgCom.setDirectoryForTemplateLoading(new File(ftlFilePathCommon));
        ArrayList<String> names=new ArrayList<>();
        names.add("application.properties");
        names.add("application-dev.properties");
        Map root = new HashMap(10);
        root.put("packageName", Config.BASE_PACKAGE);
        root.put("projectName",Config.PROJECT_NAME);
        root.put("url",Config.URL);
        root.put("user",Config.USER);
        root.put("pwd",Config.PASSWORD);
        for (int i = 0; i < names.size(); i++) {
            Template temBaseMapper = cfgCom.getTemplate(names.get(i)+".ftl");
            File pomFile = new File(resDir + separator+ names.get(i));
            Writer out = new OutputStreamWriter(new FileOutputStream(pomFile.getPath()), StandardCharsets.UTF_8);
            temBaseMapper.process(root, out);
        }
        copyFile(ftlFilePathCommon+ separator+"logback-spring.xml.ftl",resDir+ separator+"logback-spring.xml");



    }

    private void createCommon(String controllerPath) throws Exception{
        String ftlFilePathCommon = MgrCodeGenerator.class.getResource("/").getPath() + "/common";
        Configuration cfgCom = new Configuration();
        cfgCom.setDirectoryForTemplateLoading(new File(ftlFilePathCommon));
        ArrayList<String> names=new ArrayList<>();
        names.add("BizException");
        names.add("InvokeResult");
        names.add("PageResult");
        names.add("RestStatus");
        names.add("ShiftExp");
        names.add("StatusCode");
        names.add("ConvertUtil");
        names.add("CommonDTO");
        Map root = new HashMap(10);
        root.put("packageName", Config.BASE_PACKAGE);
        String savePath=controllerPath + separator+"common"+ separator;
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        for (int i = 0; i < names.size(); i++) {
            Template temBaseMapper = cfgCom.getTemplate(names.get(i)+".ftl");
            File pomFile = new File(controllerPath + separator+"common"+ separator+ names.get(i)+ ".java");
            Writer out = new OutputStreamWriter(new FileOutputStream(pomFile.getPath()), StandardCharsets.UTF_8);
            temBaseMapper.process(root, out);
        }

        System.out.println("创建common完成");
    }

    /**
     * 创建父项目
     *
     * @param projectDir
     * @param pomMap
     * @param temEntity
     */
    private void createParentProject(File projectDir, Map<String, Object> pomMap, Template temEntity) {
        System.out.println("准备创建父工程...");
        File pomFile = new File(projectDir + separator + "pom.xml");
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(pomFile.getPath()), StandardCharsets.UTF_8);
            temEntity.process(pomMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建module
     *
     * @param projectModuleSuffixName module后缀名
     * @return module路径
     * @throws IOException io异常
     */
    private String generateProjectDir(String projectModuleSuffixName) throws IOException {
        //项目module结构名字（项目名-module后缀名）
        String projectModuleName = Config.PROJECT_NAME + "-" + projectModuleSuffixName.toLowerCase();

        //生成module文件夹
        File projectModuleDir = new File(Config.SAVE_PATH_PRE + separator + Config.PROJECT_NAME + separator + projectModuleName);
        if (projectModuleDir.exists()) {
            projectModuleDir.delete();
        }
        projectModuleDir.mkdirs();

        //生成pom文件
        File pomFile = new File(projectModuleDir + separator + "pom.xml");
        if (!pomFile.exists()) {
            //找到对应POM模板
            String ftlFilePath = RESOURCES_PATH + separator + "mvcpom";
            Configuration pomConfig = new Configuration(Configuration.VERSION_2_3_28);
            pomConfig.setDirectoryForTemplateLoading(new File(ftlFilePath));
            Template temEntity = pomConfig.getTemplate(projectModuleSuffixName.replace("-", "").toLowerCase() + "POM.ftl");

            Map<String, Object> pomMap = new HashMap<>(10);
            pomMap.put("projectName", Config.PROJECT_NAME);
            pomMap.put("projectModuleName", projectModuleName);
            pomMap.put("groupId", Config.GROUP_ID);
            pomMap.put("basePackage", Config.BASE_PACKAGE);
            try {
                Writer out = new OutputStreamWriter(new FileOutputStream(pomFile.getPath()), StandardCharsets.UTF_8);
                temEntity.process(pomMap, out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //生成类文件夹
        StringBuffer basicClassDirPath = new StringBuffer(projectModuleDir + separator + "src" + separator + "main" + separator + "java");
        basicClassDirPath.append(separator + Config.BASE_PACKAGE.replace(".", separator));
        File classDir = new File(basicClassDirPath.toString());
        classDir.mkdirs();
        //如果是dao子模块，需另外生成resources.mapper文件夹,放basemapper的other文件夹
        if ("dao".equals(projectModuleSuffixName)) {
            File resDir = new File(projectModuleDir + separator + "src" + separator + "main" + separator + "resources" + separator + "mapper");
            File resDir1 = new File(basicClassDirPath + separator + "other");
            resDir.mkdirs();
            resDir1.mkdirs();
        }
        //如果是controller子模块，需另外生成resources文件夹
        if ("web".equals(projectModuleSuffixName)) {
            File resDir = new File(projectModuleDir + separator + "src" + separator + "main" + separator + "resources");
            resDir.mkdirs();
        }
        System.out.println("创建子模块：" + projectModuleName + "---路径：" + basicClassDirPath.toString());
        return basicClassDirPath.toString();
    }

    /**
     * 创建基础代码
     *
     * @param map          表数据
     * @param cfg
     * @param ftlFileName  模板名
     * @param javaFileName 文件后缀
     * @param savePath     文件存放地址
     * @throws IOException
     */
    private void createJavaFile(Map<String, List<EntityBean>> map, Configuration cfg, String ftlFileName, String javaFileName, String savePath) throws IOException {
        if (!savePath.endsWith(separator)) {
            savePath = savePath + separator;
        }
        MgrCodeGenerator.genJavaFile(map, cfg, ftlFileName, javaFileName, savePath);
    }

    /**
     * 根据模板生成文件
     *
     * @param root      模板需要的变量
     * @param temEntity 模板
     * @param filePath  文件地址
     * @param fileName  文件名
     */
    private void createFileByTemp(Map root, Template temEntity, String filePath, String fileName) {
        if (root == null) {
            root = new HashMap(1);
        }
        if (!filePath.endsWith(separator)) {
            filePath = filePath + separator;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(filePath + fileName), StandardCharsets.UTF_8);
            temEntity.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringBootProjectGeneratorVennay generator = new SpringBootProjectGeneratorVennay();
        generator.genProject();
    }

    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

}
