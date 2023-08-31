package com.youngfinance.front.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigruationSettinngs {
    //创建Docket对象
    @Bean
    public Docket docket(){
        //1创建Docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        //2创建Api信息， 接口文档的总体描述
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("YoungFinance")
                .version("1.0")
                .description("前后端分离的项目，前端Vue，后端Spring Boot + Dubbo分布式项目")
                .build();

        //3.设置使用ApiInfo
        docket = docket.apiInfo(apiInfo);

        //4.设置参与文档生成的包
        docket = docket.select().apis(RequestHandlerSelectors.
                     basePackage("com.youngfinance.front.controller")).build();
        return docket;
    }
}
