package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

/**
 * @ClassName:FirstInterceptor
 * @Author:LiGou
 * @Date:2023/5/5 14:38
 * @Version:1.0
 * @Description:
 */
@Component
@EnableWebMvc
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class InterceptorConfig
        implements WebMvcConfigurer {

//    @Autowired
//    FirstInterceptor firstInterceptor;
//
//    @Autowired
//    SecondInterceptor secondInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(firstInterceptor);
////        registry.addInterceptor(secondInterceptor);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
