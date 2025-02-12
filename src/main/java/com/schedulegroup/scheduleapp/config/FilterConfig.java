package com.schedulegroup.scheduleapp.config;

import com.schedulegroup.scheduleapp.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/member/search/*", "/member/edit/*", "/member/delete/*", "/member/logout/*");
        registrationBean.addUrlPatterns("/schedule/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
