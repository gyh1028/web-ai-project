package com.ahu.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射本地图片路径到 /images/** URL
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/javaweb/web-ai-project/images/");
    }
}
