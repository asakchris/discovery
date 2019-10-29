package com.example.welcome.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "random", url = "${app.feign.random.url}")
public interface RandomClient {
    @RequestMapping(method = RequestMethod.GET, value = "/display")
    Integer getRandomNumber();
}
