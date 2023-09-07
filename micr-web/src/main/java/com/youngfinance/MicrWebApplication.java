package com.youngfinance;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.youngfinance.common.util.JwtUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 启动Swagger 和 ui
@EnableSwaggerBootstrapUI
@EnableSwagger2
// @EnableWebMvc
// 启动Dubbo服务
@EnableDubbo
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MicrWebApplication {

    @Value("${jwt.secret}")
    private String secretKey;
    @Bean
    public JwtUtil jwtUtil() {
        JwtUtil jwtUtil = new JwtUtil(secretKey);

        return jwtUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
