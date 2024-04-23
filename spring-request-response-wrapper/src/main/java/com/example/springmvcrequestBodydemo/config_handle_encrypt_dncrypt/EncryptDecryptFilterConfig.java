package com.example.springmvcrequestBodydemo.config_handle_encrypt_dncrypt;

import com.example.springmvcrequestBodydemo.config_handle_requestmissing.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class EncryptDecryptFilterConfig {

    @Bean
    public FilterRegistrationBean encryptDecryptFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new EncryptDecryptFilter());
        filterFilterRegistrationBean.addUrlPatterns("/index");
        filterFilterRegistrationBean.setName("EncryptDecryptFilter");
        filterFilterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);

        return filterFilterRegistrationBean;
    }
}
