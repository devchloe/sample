package com.example.demo.authentication;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BearerAuthRequestInterceptor implements RequestInterceptor {
    private final String headerValue;

    public BearerAuthRequestInterceptor(String token) {
        checkNotNull(token);
        this.headerValue = "Bearer " + token;
    }

    private void checkNotNull(String token) {
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", new String[]{headerValue});
    }
}
