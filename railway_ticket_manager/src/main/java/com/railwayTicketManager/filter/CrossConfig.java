package com.railwayTicketManager.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: meizx
 * @ClassName: CrossConfig
 * @Date: 2019-07-04 16:01
 * @E-mail： meizxbj@gmail.com
 * @Description:跨域配置
 * @Version: V1.0.0
 */

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }
}
