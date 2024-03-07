package com.spring.core;

import com.spring.core.logTrace.LogTrace;
import com.spring.core.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configration {
    @Bean
    public LogTrace logTrace(){
        // return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }



}
