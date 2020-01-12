package com.example.demo.tool.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", primary = false)
public interface GithubApiClient {

    @Headers("Accept")
    @GetMapping("/{name}")
    public String hello(@PathVariable String name);
}
