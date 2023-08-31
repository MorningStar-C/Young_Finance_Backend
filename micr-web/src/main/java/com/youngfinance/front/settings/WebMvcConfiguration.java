package com.youngfinance.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    // 处理跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("=========================addCorsMapping");
        registry.addMapping("/**")                                   // 表示要处理的请求地址，拦截这些地址(/**)，使用跨域处理逻辑
                .allowedOriginPatterns("http://localhost:8081")                                    // 表示可跨域的域名，来源地址
                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS")        // 允许跨域的请求方式
                .allowCredentials(true)                                        // 表示是否允许浏览器发送cookie
                .maxAge(3600)                                                  // 表示本次预检有效期 （option请求）之后才post请求
                .allowedHeaders("*");                                          // 支持的跨域请求头，在请求头包含哪些数据时，可以支持跨域功能
    }
}
