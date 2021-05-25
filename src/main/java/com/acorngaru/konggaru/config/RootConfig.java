package com.acorngaru.konggaru.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Import({
        DatabaseConfig.class,
        MybatisConfig.class
})
@Configuration
@ComponentScan(
        basePackages = "com.acorngaru.konggaru",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = { Controller.class }
                )
        }
)
public class RootConfig {
}
