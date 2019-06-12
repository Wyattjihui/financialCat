package com.kw.manager;

import com.kw.swagger.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EntityScan(basePackages = {"com.kw.entity"})
@Import(SwaggerConfiguration.class)  /* 在manager中要想使用SwaggerConfiguration配置类，需要import进来 */
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
}
