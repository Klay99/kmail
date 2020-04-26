package com.wl.kmail.config.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: 自定义资源映射
 *     设置虚拟路径，访问绝对路径下资源
 */
@ComponentScan
@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {

    @Value("${app.filePath}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("file:///"+filePath);

//        registry.addResourceHandler("/static/**").addResourceLocations("file:///"+"G:/img/");
    }
}
