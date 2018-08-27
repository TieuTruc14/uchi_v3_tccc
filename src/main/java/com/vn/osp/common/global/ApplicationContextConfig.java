package com.vn.osp.common.global;

import com.vn.osp.common.util.SystemProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;

/**
 * Created by Admin on 28/7/2017.
 */
@Configuration
@EnableWebMvc
/*@EnableTransactionManagement*/
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

    /*@Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = SystemProperties.getProperty("system_user_folder");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        String dirTrue = dir.getAbsolutePath();
        registry
                .addResourceHandler("/tmpFiles*//**")
                .addResourceLocations("file:///"+dir.getAbsolutePath());
    }
}
