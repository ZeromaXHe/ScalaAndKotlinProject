package com.zerox.chapter2;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:07
 */
public interface Controller {
    HttpResponse handleRequest(HttpRequest httpRequest);
}
