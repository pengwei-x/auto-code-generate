package com.yunzhitx.medical.controller.utils.redis;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description: Hikari配置

 * @Createed Date: 2018/6/13-下午8:05

 **/
@Configuration
public class HikariCpDateSourceConfig extends DataSourceAutoConfiguration {
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties properties) {
        return DataSourceBuilder.create(properties.getClassLoader()).type(HikariDataSource.class)
                .driverClassName(properties.determineDriverClassName())
                .url(properties.determineUrl()).username(properties.determineUsername()).password(properties.determinePassword()).build();
    }
}
