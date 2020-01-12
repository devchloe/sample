package com.example.demo.tool.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="gitlab", primary = false)
public interface GitlabApiClient {
    @GetMapping("/{name}")
    public String hello(@PathVariable String name);
}
