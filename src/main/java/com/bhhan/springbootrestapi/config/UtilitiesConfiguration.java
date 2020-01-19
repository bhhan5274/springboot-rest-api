package com.bhhan.springbootrestapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hbh5274@gmail.com on 2020-01-19
 * Github : http://github.com/bhhan5274
 */

@Configuration
public class UtilitiesConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
