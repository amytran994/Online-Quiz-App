package com.project.QuizDemo.config;

import com.project.QuizDemo.controller.AdminFilter;
import com.project.QuizDemo.controller.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginfilter()
    {
        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new LoginFilter());
        bean.addUrlPatterns("/login");

        return bean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> filter()
    {
        FilterRegistrationBean<AdminFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new AdminFilter());
        bean.addUrlPatterns("/admin");

        return bean;
    }

}