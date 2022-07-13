package com.zerox.chapter2;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:15
 */
public class TinyWeb {
    private Map<String, Controller> controllers;
    private List<Filter> filters;

    public TinyWeb(Map<String, Controller> controllers, List<Filter> filters) {
        this.controllers = controllers;
        this.filters = filters;
    }

    public HttpResponse handleRequest(HttpRequest httpRequest) {
        HttpRequest currentRequest = httpRequest;
        for (Filter filter : filters) {
            currentRequest = filter.doFilter(currentRequest);
        }
        Controller controller = controllers.get(httpRequest.getPath());
        if (null == controller) {
            return null;
        }
        return controller.handleRequest(currentRequest);
    }
}
