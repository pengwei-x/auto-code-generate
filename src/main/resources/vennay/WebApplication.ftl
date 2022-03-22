package ${packageName}.web;

import com.yunzhicloud.core.Constants;
import com.yunzhicloud.web.YzCloudApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 * @author codeGenerate
 */
@ServletComponentScan
@ComponentScan(Constants.BASE_PACKAGES)
@MapperScan(basePackages = "${packageName}.mapper")
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        YzCloudApplication.run("iot-xxx", WebApplication.class, args);
    }
    /**
    * 分页插件
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}