package com.mouensis.framework.context.configuration;

import com.mouensis.framework.context.filter.ContentCachingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author zhuyuan
 * @date 2020/12/1 16:31
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public ContentCachingFilter contentCachingFilter() {
        return new ContentCachingFilter();
    }

    @Bean
    public FilterRegistrationBean<ContentCachingFilter> contentCachingFilterRegistration(ContentCachingFilter filter) {
        FilterRegistrationBean<ContentCachingFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setOrder(filter.getOrder());
        return registration;
    }
}
