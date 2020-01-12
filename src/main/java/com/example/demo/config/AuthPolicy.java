package com.example.demo.config;

import com.example.demo.authentication.BearerAuthRequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.stereotype.Component;

@Component
public class AuthPolicy {
    public static BasicAuthRequestInterceptor basicType(String id, String password) {
        return new BasicAuthRequestInterceptor(id, password);
    }

    public static BearerAuthRequestInterceptor bearerType(String token) {
        return new BearerAuthRequestInterceptor(token);
    }
}
