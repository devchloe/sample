package com.example.demo.config;

import com.example.demo.tool.client.GithubApiClient;
import com.example.demo.tool.client.GitlabApiClient;
import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FeignApiClientBuilder {

    // here: 새로운 연계 시스템이 생길 때 추가해줘야 하는 부분
    public static GithubApiClient getGithubApiClient(String url, RequestInterceptor... interceptors) {
        return getTargetApiClient(GithubApiClient.class, url, interceptors);
    }

    public static GitlabApiClient getGitlabApiClient(String url, RequestInterceptor... interceptors) {
        return getTargetApiClient(GitlabApiClient.class, url, interceptors);
    }

    // here: 공통
    private static <T> T getTargetApiClient(Class<T> apiType, String url, RequestInterceptor[] interceptors) {
        return getBuilder(interceptors).target(apiType, url);
    }

    private static Feign.Builder getBuilder(RequestInterceptor... interceptors) {
        return Feign.builder().contract(new SpringMvcContract())
                .requestInterceptors(Arrays.asList(interceptors));
    }
}
