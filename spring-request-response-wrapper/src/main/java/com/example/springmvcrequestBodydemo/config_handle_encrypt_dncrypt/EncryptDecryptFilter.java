package com.example.springmvcrequestBodydemo.config_handle_encrypt_dncrypt;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncryptDecryptFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        ServletResponse responseWrapper = new EncryptResponseWrapper((HttpServletResponse) response);
        // 假设前后端只通过json交互
        if (request instanceof HttpServletRequest && StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            requestWrapper = new DecryptRequestWrapper((HttpServletRequest) request);
        }

        if (requestWrapper == null) {
            chain.doFilter(request, responseWrapper);
        } else {
            chain.doFilter(requestWrapper, responseWrapper);
        }

        //在响应刷新后进行加密处理
        responseWrapper.flushBuffer();
    }

}
