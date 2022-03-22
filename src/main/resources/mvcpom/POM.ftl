<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${projectName}</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
    </parent>

    <name>${projectName}</name>
    <url>http://maven.apache.org</url>
    <modules>
    <#list moduleList as module>
        <module>${module}</module>
    </#list>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <version.HikariCP>2.7.8</version.HikariCP>
        <version.mapper>4.0.1</version.mapper>
        <version.mybatis.pagehelper>5.1.2</version.mybatis.pagehelper>
        <version.springboot.mybatis.pagehelper>1.2.3</version.springboot.mybatis.pagehelper>
        <version.lombok>1.18.12</version.lombok>
        <version.swagger.knife4j.ui>2.0.1</version.swagger.knife4j.ui>
        <version.mybatis-plus>3.1.2</version.mybatis-plus>
        <version.mybatis.spring>2.0.1</version.mybatis.spring>
        <version.knife4j>2.0.4</version.knife4j>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <!-- mybatis orm-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>${'$'?html}{version.mybatis.pagehelper}</version>
            </dependency>
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>${'$'?html}{version.springboot.mybatis.pagehelper}</version>
            </dependency>
            <!--datasource-->
            <dependency>
                <groupId>com.zaxxer</groupId>
                <artifactId>HikariCP</artifactId>
                <version>${'$'?html}{version.HikariCP}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${'$'?html}{version.lombok}</version>
            </dependency>
            <!-- mybatisPlus -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${'$'?html}{version.mybatis-plus}</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-annotation</artifactId>
                <version>${'$'?html}{version.mybatis-plus}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>${'$'?html}{version.mybatis.spring}</version>
            </dependency>
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-spring-boot-starter</artifactId>
                <version>${'$'?html}{version.knife4j}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
