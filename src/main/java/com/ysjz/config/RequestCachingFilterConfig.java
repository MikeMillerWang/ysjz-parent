package com.ysjz.config;

import com.ysjz.wrapper.MyRequestWrapper1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局请求包装过滤器：解决流只能读一次问题
 */
@Slf4j
@Configuration
public class RequestCachingFilterConfig {

    @Bean
    public FilterRegistrationBean requestCachingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        // 核心：创建过滤器，包装请求为 ContentCachingRequestWrapper
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                // 1. 仅包装 HTTP 请求（排除 WebSocket 等）
                if (request instanceof HttpServletRequest && !(request instanceof ContentCachingRequestWrapper)) {
                    log.info("==========进入requestCachingFilter========");
                    // 2. 包装请求（自动缓存请求体）
                    MyRequestWrapper1 wrappedRequest = new MyRequestWrapper1(request);
                    filterChain.doFilter(wrappedRequest, response); // 传递包装后的请求
                } else {
                    filterChain.doFilter(request, response); // 无需包装，直接放行
                }
            }
        });

        // 3. 配置拦截所有请求（可根据需求调整 URL 模式）
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // 优先级最高，确保先于其他过滤器执行
        registrationBean.setName("requestCachingFilter");
        return registrationBean;
    }
}