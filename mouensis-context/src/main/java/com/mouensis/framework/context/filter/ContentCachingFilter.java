package com.mouensis.framework.context.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuyuan
 * @date 2020/12/1 12:01
 */
@Slf4j
public class ContentCachingFilter implements Filter, Ordered {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.trace("重写request，response可重复读取body---------start-----------");
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(requestWrapper, responseWrapper);
        log.trace("重写request，response可重复读取body----------end----------");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
