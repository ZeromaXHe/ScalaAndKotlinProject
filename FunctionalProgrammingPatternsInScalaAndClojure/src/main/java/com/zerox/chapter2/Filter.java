package com.zerox.chapter2;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:14
 */
public interface Filter {
    HttpRequest doFilter(HttpRequest request);
}
