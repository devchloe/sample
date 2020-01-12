package com.example.demo.sample;

import com.example.demo.config.AuthPolicy;
import com.example.demo.authentication.BearerAuthRequestInterceptor;
import com.example.demo.config.FeignApiClientBuilder;
import com.example.demo.tool.client.GithubApiClient;
import com.example.demo.tool.client.GitlabApiClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final FeignApiClientBuilder builder;

    private final AuthPolicy authPolicy;

    public String hello(String name) {
        RequestInterceptor interceptor = new BasicAuthRequestInterceptor("mayaul", "1234567890");
        RequestInterceptor interceptor2 = new BearerAuthRequestInterceptor("token1323412");

        return builder.getGithubApiClient("http://localhost:9001", interceptor, interceptor2).hello(name);
    }

    public String hello2(String name) {
        RequestInterceptor interceptor = new BearerAuthRequestInterceptor("token1323412");

        return builder.getGithubApiClient("http://localhost:9001", interceptor).hello(name);
    }


    // 사용성을 위한 변형

    public String helloWithoutAuthorization(String name) {
        return getGithubApiClient("http://localhost:9001").hello(name);
    }

    public String helloUsingBasicAuthType(String name) {
        return getGithubApiClient("http://localhost:9001", authPolicy.basicType("mayaul", "1234567890")).hello(name);
    }

    public String helloUsingBearerAuthType(String name) {
        return getGithubApiClient("http://localhost:9001", authPolicy.bearerType("token1323412")).hello(name);
    }


    // here: apiClient별 auth 정책턴(none, basic, bearer)에 따라 생성한 apiClient를 리턴
    // 호출부에서 requestInterceptor의 존재를 모르도록 추상화 하기 -> AuthPolicy를 이용해서 interceptor 생성


    // here: 새로운 연계 시스템이 생길 때 추가해줘야 하는 부분 -> FeignApiClientBuilder를 이용해서 원하는 client를 생성
    private GithubApiClient getGithubApiClient(String url, RequestInterceptor... interceptors) {
        return getTargetApiClient(GithubApiClient.class, url, interceptors);
    }

    private GitlabApiClient getGitlabApiClient(String url, RequestInterceptor... interceptors) {
        return getTargetApiClient(GitlabApiClient.class, url, interceptors);
    }

    // here: 공통
    private <T> T getTargetApiClient(Class<T> apiType, String url, RequestInterceptor[] interceptors) {
        return getBuilder(interceptors).target(apiType, url);
    }

    private Feign.Builder getBuilder(RequestInterceptor... interceptors) {
        return Feign.builder().contract(new SpringMvcContract())
                .requestInterceptors(Arrays.asList(interceptors));
    }
}
