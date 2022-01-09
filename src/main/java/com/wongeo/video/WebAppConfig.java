package com.wongeo.video;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

//上传配置类
//图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cbs.filesPath}")
    private String filesPath;

    @Value("${cbs.filesPath2}")
    private String filesPath2;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/files/**")
                    .addResourceLocations(filesPath2);
        }else {
            registry.addResourceHandler("/files/**")
                    .addResourceLocations(filesPath);
        }

        super.addResourceHandlers(registry);
    }
}