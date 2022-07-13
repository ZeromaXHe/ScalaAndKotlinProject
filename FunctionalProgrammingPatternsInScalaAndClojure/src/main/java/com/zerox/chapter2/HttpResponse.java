package com.zerox.chapter2;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 10:45
 */
public class HttpResponse {
    private final String body;
    private final Integer responseCode;

    public String getBody() {
        return body;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    private HttpResponse(Builder builder) {
        body = builder.body;
        responseCode = builder.responseCode;
    }

    public static class Builder {
        private String body;
        private Integer responseCode;

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder responseCode(Integer responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
