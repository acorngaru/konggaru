package com.acorngaru.konggaru.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.acorngaru.konggaru",
        includeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        classes = { Controller.class }
                )
        }
)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("/css/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }
}
