package com.kw.swagger.config;

import com.kw.swagger.SwaggerInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.kw.swagger")  /* 扫描基础包 */
@EnableSwagger2    /* 创建一个configuration类配置文件就可以管理我们文档。 */
public class SwaggerConfiguration {

    @Autowired
    private SwaggerInfo swaggerInfo;

    @Bean
    public Docket controllerApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .apiInfo(apiInfo());
                /*//只显示指定包下的接口
                .select().apis(RequestHandlerSelectors.basePackage(ProductController.class.getPackage().getName()))
                //通过url来控制接口的显示
                .paths(PathSelectors.ant("/products/*"))*/
        ApiSelectorBuilder builder = docket.select();
        if (!StringUtils.isEmpty(swaggerInfo.getBasePackage())){
            builder = builder.apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()));
        }
        if (!StringUtils.isEmpty(swaggerInfo.getAntPath())){
            builder = builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
        }
        return builder.build();
    }

    //分组来区分多种情况的接口，部分接口可能对外使用，部分接口内部使用
    @Bean
    public Docket defaultApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerInfo.getTitle())
                .description(swaggerInfo.getDescription())
                .termsOfServiceUrl("http://springfox.io")
                .contact("bawei")
                .license(swaggerInfo.getLicense())
                .version("2.0")
                .build();
    }
}
