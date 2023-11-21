package com.example.event.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class BeanConfigurer {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
