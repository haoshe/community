package com.haoshe.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {
    /*
    Registers the returned object as a Spring bean.
    Bean name defaults to the method name: "simpleDateFormat".
    Often used to create beans from third-party classes or library objects.
     */
    @Bean
    public SimpleDateFormat simpleDateFormat(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
