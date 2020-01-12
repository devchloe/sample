package com.example.demo.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/github/{name}")
    public String helloGithub(@PathVariable String name) {
        return sampleService.hello(name);
    }

    @GetMapping("/github2/{name}")
    public String helloGithub2(@PathVariable String name) {
        return sampleService.hello2(name);
    }

    @GetMapping("/github3/{name}")
    public String helloGithub3(@PathVariable String name) {
        return sampleService.helloUsingBasicAuthType(name);
    }

    @GetMapping("/github4/{name}")
    public String helloGithub4(@PathVariable String name) {
        return sampleService.helloUsingBearerAuthType(name);
    }

    @GetMapping("/github5/{name}")
    public String helloGithub5(@PathVariable String name) {
        return sampleService.helloWithoutAuthorization(name);
    }

//    @GetMapping("/gitlab/{name}")
//    public String helloGitlab(@PathVariable String name) {
//        return "gitlab";
//        return factory.gitlabApiClient("http://localhost:9002/").hello(name);
//    }

}
