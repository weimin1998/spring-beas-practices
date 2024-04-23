package com.example.springmvcrequestBodydemo.config_handle_requestmissing;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean requestFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new RequestFilter());
        filterFilterRegistrationBean.addUrlPatterns("/hello");
        filterFilterRegistrationBean.setName("RequestFilter");
        filterFilterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);

        return filterFilterRegistrationBean;
    }
}
