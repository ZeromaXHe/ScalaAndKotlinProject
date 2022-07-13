package com.zerox.chapter2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 10:45
 */
public class HttpRequest {
    private final String path;
    private final String body;
    private final Map<String, String> headers;

    public String getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    private HttpRequest(Builder builder) {
        body = builder.body;
        path = builder.path;
        headers = builder.headers;
    }

    public static class Builder {
        private String body;
        private String path;
        private Map<String, String> headers;

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder addHeader(String headerName, String headerValue) {
            if (this.headers == null) {
                this.headers = new HashMap<String, String>();
            }
            this.headers.put(headerName, headerValue);
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static Builder builderFrom(HttpRequest request) {
            Builder builder = new Builder();
            builder.path(request.getPath());
            builder.body(request.getBody());
            Map<String, String> headers = request.getHeaders();
            for (String headerName : headers.keySet()) {
                builder.addHeader(headerName, headers.get(headerName));
            }
            return builder;
        }
    }
}
