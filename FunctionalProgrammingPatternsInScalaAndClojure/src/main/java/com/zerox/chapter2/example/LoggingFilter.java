package com.zerox.chapter2.example;

import com.zerox.chapter2.Filter;
import com.zerox.chapter2.HttpRequest;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:26
 */
public class LoggingFilter implements Filter {
    @Override
    public HttpRequest doFilter(HttpRequest request) {
        System.out.println("In Logging Filter - request for path: " + request.getPath());
        return request;
    }
}
