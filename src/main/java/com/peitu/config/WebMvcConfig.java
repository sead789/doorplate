package com.peitu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 加载静态资源
 *
 * @author Rising
 * @date 2019/6/27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger
        //把静态资源加载进来，防止http://{url}:{port}/swagger-ui.html访问不到
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //系统静态资源
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }

}
